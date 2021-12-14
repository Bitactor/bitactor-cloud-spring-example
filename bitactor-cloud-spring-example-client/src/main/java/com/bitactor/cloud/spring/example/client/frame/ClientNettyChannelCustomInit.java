package com.bitactor.cloud.spring.example.client.frame;

import com.bitactor.framework.cloud.spring.boot.client.net.ClientNettyChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;
import io.netty.channel.ChannelOption;
import org.springframework.stereotype.Component;

/**
 * @author WXH
 */
@Component
public class ClientNettyChannelCustomInit implements ClientNettyChannelInit {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty client 端相关的 Options
        System.out.println(">>>>>> ClientNettyChannelCustomInit: " + initOperate.getV(ChannelOption.TCP_NODELAY));
    }
}
