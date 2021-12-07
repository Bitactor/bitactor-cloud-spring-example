package com.bitactor.cloud.spring.example.cluster.service1.frame;

import com.bitactor.framework.cloud.spring.boot.cluster.sender.ConsumerChannelNettySendPolicy;
import com.bitactor.framework.core.exception.NetLimitWritableException;
import com.bitactor.framework.core.net.api.transport.message.MessageWrapper;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author WXH
 */
@Slf4j
@Component
public class ConsumerSenderPolicy implements ConsumerChannelNettySendPolicy {

    @Override
    public ChannelFuture sendHandler(MessageWrapper message, ChannelHandlerContext ctx, ChannelPromise channelPromise) {
        // 可以实现自定义的消息发送策略，默认当缓存区不可写时，抛弃当前消息
        if (ctx.channel().isWritable()) {
            ChannelFuture channelFuture = ctx.writeAndFlush(message, channelPromise);
            log.info("Do ConsumerSenderPolicy method sendHandler  channelId:{},data type: {}", ctx.channel().id(), message.getClass().getSimpleName());
            return channelFuture;
        }
        channelPromise.setFailure(new NetLimitWritableException("Can not writable msg"));
        return channelPromise;
    }
}
