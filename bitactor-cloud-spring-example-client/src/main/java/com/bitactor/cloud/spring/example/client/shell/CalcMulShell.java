/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bitactor.cloud.spring.example.client.shell;

import com.bitactor.cloud.spring.example.client.manager.ConnectorClient;
import com.bitactor.cloud.spring.example.client.manager.ConnectorClientManager;
import com.bitactor.cloud.spring.example.proto.CalcMulReq;
import com.bitactor.cloud.spring.example.proto.CalcMulResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * 获取登录玩家
 *
 * @author WXH
 */
@Slf4j
@ShellComponent
public class CalcMulShell {
    private final ConnectorClientManager connectorClientManager;

    public CalcMulShell(ConnectorClientManager connectorClientManager) {
        this.connectorClientManager = connectorClientManager;
    }

    @ShellMethod(value = "计算乘法", key = "mul", group = "Cal")
    public String calcMul(@ShellOption(help = "uid") String uid, @ShellOption(help = "参数A") int a, @ShellOption(help = "参数B") int b) {
        try {
            ConnectorClient connectorClient = connectorClientManager.get(uid);
            CalcMulResp calcAdd = connectorClient.sendSync(new CalcMulReq(a, b), CalcMulResp.class, connectorClient.getRequestStage("calcMul"));
            return "执行乘法结果：" + calcAdd.getResult();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return "计算乘法失败";
        }
    }
}
