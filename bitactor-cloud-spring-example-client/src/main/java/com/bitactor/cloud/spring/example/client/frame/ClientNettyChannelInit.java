package com.bitactor.cloud.spring.example.client.frame;

import com.bitactor.framework.core.net.api.ChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;

/**
 * @author WXH
 */
public class ClientNettyChannelInit implements ChannelInit<ChannelNettyOptions> {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty client 端相关的 Options
    }
}
