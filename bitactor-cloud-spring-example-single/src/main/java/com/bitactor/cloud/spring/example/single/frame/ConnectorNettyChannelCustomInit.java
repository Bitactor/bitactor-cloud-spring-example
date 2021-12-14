package com.bitactor.cloud.spring.example.single.frame;

import com.bitactor.framework.cloud.spring.boot.connector.net.ConnNettyChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;
import io.netty.channel.ChannelOption;
import org.springframework.stereotype.Component;

/**
 * @author WXH
 */
@Component
public class ConnectorNettyChannelCustomInit implements ConnNettyChannelInit {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty connector 端相关的 Options
        System.out.println(">>>>>> ConnectorNettyChannelCustomInit: " + initOperate.getV(ChannelOption.TCP_NODELAY));
    }
}
