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
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Collection;

/**
 * 获取登录玩家
 *
 * @author WXH
 */
@Slf4j
@ShellComponent
public class ConnectorsShell {
    private final ConnectorClientManager connectorClientManager;

    public ConnectorsShell(ConnectorClientManager connectorClientManager) {
        this.connectorClientManager = connectorClientManager;
    }

    @ShellMethod(value = "展示连接数据", key = "connectors", group = "Connect")
    public String connector() {
        Collection<ConnectorClient> all = connectorClientManager.all();
        StringBuilder sb = new StringBuilder();
        for (ConnectorClient connectorClient : all) {
            sb.append("connector: ").append(connectorClient.getUid()).append("\n");
        }
        sb.append("connector size: ").append(all.size());
        return sb.toString();
    }
}
