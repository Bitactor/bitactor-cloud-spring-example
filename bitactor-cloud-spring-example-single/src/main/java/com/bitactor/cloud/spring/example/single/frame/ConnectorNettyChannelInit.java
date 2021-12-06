package com.bitactor.cloud.spring.example.single.frame;

import com.bitactor.framework.core.net.api.ChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;

/**
 * @author WXH
 */
public class ConnectorNettyChannelInit implements ChannelInit<ChannelNettyOptions> {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty connector 端相关的 Options
    }
}
