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

import com.bitactor.cloud.spring.example.client.manager.ConnectorClientManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Objects;

/**
 * 获取登录玩家
 *
 * @author WXH
 */
@Slf4j
@ShellComponent
public class ConnectShell {
    private final ConnectorClientManager connectorClientManager;

    public ConnectShell(ConnectorClientManager connectorClientManager) {
        this.connectorClientManager = connectorClientManager;
    }

    @ShellMethod(value = "新建一个新的连接", key = "connect", group = "Connect")
    public String connect(@ShellOption(help = "uid") String uid) {
        try {
            if (Objects.isNull(connectorClientManager.connect(uid))) {
                return "连接成功";
            } else {
                return "已存在该连接";
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return "连接失败";
        }

    }
}
