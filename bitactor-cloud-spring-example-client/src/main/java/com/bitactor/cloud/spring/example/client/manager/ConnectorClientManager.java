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

package com.bitactor.cloud.spring.example.client.manager;

import com.bitactor.framework.cloud.spring.boot.client.BitactorClientProperties;
import com.bitactor.framework.cloud.spring.boot.client.config.SpringClientConfig;
import com.bitactor.framework.cloud.spring.boot.client.extension.ClientManager;
import com.bitactor.framework.cloud.spring.boot.client.net.ClientChannelManager;
import com.bitactor.framework.cloud.spring.boot.client.net.ClientNettyChannelInit;
import com.bitactor.framework.cloud.spring.boot.client.sender.ClientChannelNettySendPolicy;
import com.bitactor.framework.core.config.UrlProperties;
import com.bitactor.framework.core.net.netty.client.NettyModeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WXH
 */
@Slf4j
@Component
public class ConnectorClientManager implements ClientManager<ConnectorClient, String> {

    private final ConcurrentHashMap<String, ConnectorClient> connectors = new ConcurrentHashMap<>();

    private AtomicInteger index = new AtomicInteger(0);
    @Resource
    private BitactorClientProperties bitactorClientProperties;
    @Autowired(required = false)
    private ClientChannelNettySendPolicy clientChannelNettySendPolicy;
    @Autowired(required = false)
    private ClientNettyChannelInit clientNettyChannelInit;

    @Override
    public ConnectorClient get(String uid) {
        return connectors.get(uid);
    }


    @Override
    public void add(ConnectorClient connectorClient) {
        connectors.put(connectorClient.getUid(), connectorClient);
    }

    @Override
    public ConnectorClient remove(String uid) {
        ConnectorClient client = connectors.remove(uid);
        return client;
    }


    @Override
    public Collection<ConnectorClient> all() {
        return connectors.values();
    }

    @Override
    public ConnectorClient buildNext() {
        return null;
    }

    public ConnectorClient connect(String uid) throws Throwable {
        ConnectorClient oldConnectClient = connectors.get(uid);
        if (Objects.nonNull(oldConnectClient)) {
            return oldConnectClient;
        }
        SpringClientConfig clientConfig = bitactorClientProperties.getClient();
        UrlProperties urlProperties = clientConfig.toUrl();
        ConnectorClient connectorClient = new ConnectorClient(uid);
        NettyModeClient client = new NettyModeClient(new ClientChannelManager(connectorClient, this, clientChannelNettySendPolicy), urlProperties, clientNettyChannelInit);
        connectorClient.setClient(client);
        client.threadStart().sync();
        connectorClient.init();
        this.add(connectorClient);
        return null;
    }

    @Override
    public int startCount() {
        return 0;
    }
}
