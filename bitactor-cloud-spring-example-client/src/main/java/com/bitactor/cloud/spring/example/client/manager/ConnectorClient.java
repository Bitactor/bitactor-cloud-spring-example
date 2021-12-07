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


import com.bitactor.cloud.spring.example.client.notice.TimeNotifyListener;
import com.bitactor.framework.cloud.spring.boot.client.extension.AbstractClientEntity;
import com.bitactor.framework.cloud.spring.boot.client.extension.RequestStage;
import com.bitactor.framework.core.net.api.Channel;
import com.bitactor.framework.core.net.api.Client;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;


/**
 * @author WXH
 */
@Slf4j
public class ConnectorClient extends AbstractClientEntity<String> {

    private String uid;
    private Client<ChannelFuture> client;
    private boolean activity;

    public ConnectorClient(String uid) {
        super();
        this.uid = uid;
    }

    @Override
    public String getUid() {
        return this.uid;
    }

    @Override
    public void init() {
        initNotice();
    }

    private void initNotice() {
        addNoticeMapping(new TimeNotifyListener());
    }

    @Override
    public Channel<ChannelFuture> getChannel() {
        return client.getChannel();
    }

    @Override
    public Client<ChannelFuture> getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void onActivity() {
        this.activity = true;
    }


    @Override
    public void onDestroy() {
        this.activity = false;
    }


    @Override
    public void doClose() {
        log.info("Do close uid:{}",  this.uid);
        if (getChannel() != null) {
            getChannel().close();
        }
    }

    public boolean isActivity() {
        return activity;
    }


    public RequestStage getRequestStage(String reqName) {
        long start = System.currentTimeMillis();
        final boolean[] exception = {false};
        StringBuilder sb = new StringBuilder();
        return new RequestStage() {
            @Override
            public void before() {
                sb.append("\n------------------------------------------------------消息请求--------------------------------------------------").append("\n");
                sb.append("====发送请求:").append(reqName).append("\n");
                sb.append("====玩家UID:").append(getUid()).append("\n");
            }

            @Override
            public void after() {
                sb.append("====方法耗时:").append(System.currentTimeMillis() - start).append("ms\n");
                sb.append("====是否异常:").append(exception[0]).append("\n");
                sb.append("------------------------------------------------------消息请求--------------------------------------------------").append("\n");
                log.debug(sb.toString());
            }

            @Override
            public void exception(Throwable throwable) {
                exception[0] = true;
            }
        };
    }

}
