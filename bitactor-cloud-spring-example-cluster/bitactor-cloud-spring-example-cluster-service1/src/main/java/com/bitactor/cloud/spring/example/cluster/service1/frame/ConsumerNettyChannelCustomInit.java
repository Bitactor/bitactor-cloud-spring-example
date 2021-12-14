package com.bitactor.cloud.spring.example.cluster.service1.frame;

import com.bitactor.framework.cloud.spring.boot.cluster.net.ConsumerNettyChannelInit;
import com.bitactor.framework.core.net.netty.channel.ChannelNettyOptions;
import io.netty.channel.ChannelOption;
import org.springframework.stereotype.Component;

/**
 * @author WXH
 */
@Component
public class ConsumerNettyChannelCustomInit implements ConsumerNettyChannelInit {

    @Override
    public void init(ChannelNettyOptions initOperate) {
        // TODO 可以设置 netty RPC consumer 端相关的 Options
        System.out.println(">>>>>> ConsumerNettyChannelCustomInit: " + initOperate.getV(ChannelOption.TCP_NODELAY));
    }
}
