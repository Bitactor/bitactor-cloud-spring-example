package com.bitactor.cloud.spring.example.cluster.service1.frame;

import com.bitactor.framework.cloud.spring.boot.cluster.net.ProviderNettyChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;
import io.netty.channel.ChannelOption;
import org.springframework.stereotype.Component;

/**
 * @author WXH
 */
@Component
public class ProviderNettyChannelCustomInit implements ProviderNettyChannelInit {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty RPC provider 端相关的 Options
        System.out.println(">>>>>> ProviderNettyChannelCustomInit: " + initOperate.getV(ChannelOption.TCP_NODELAY));
    }
}
