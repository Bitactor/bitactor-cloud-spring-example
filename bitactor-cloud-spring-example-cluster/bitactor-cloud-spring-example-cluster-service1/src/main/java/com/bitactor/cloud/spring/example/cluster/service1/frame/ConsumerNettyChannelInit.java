package com.bitactor.cloud.spring.example.cluster.service1.frame;

import com.bitactor.framework.core.net.api.ChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;

/**
 * @author WXH
 */
public class ConsumerNettyChannelInit implements ChannelInit<ChannelNettyOptions> {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty RPC consumer 端相关的 Options
    }
}
