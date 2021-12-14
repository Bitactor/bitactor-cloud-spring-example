package com.bitactor.cloud.spring.example.single.job;

import com.bitactor.cloud.spring.example.proto.TimeNotify;
import com.bitactor.framework.cloud.spring.controller.extension.ConnectorChannelHandler;
import com.bitactor.framework.cloud.spring.model.codec.MessageConnectorData;
import com.bitactor.framework.cloud.spring.model.utils.MessageUtil;
import com.bitactor.framework.core.net.api.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author WXH
 */
@Slf4j
@Component
public class WorkJob {
    @Resource
    private ConnectorChannelHandler connectorChannelHandler;

    /**
     * 当前承载连接数
     */
    @Scheduled(fixedDelay = 30000)
    private void printConnectSize() {
        Collection<Channel<ChannelFuture>> channels = connectorChannelHandler.getConnectorChannels();
        log.info("Number of client connections：{}", channels.size());
        TimeNotify timeNotify = new TimeNotify(System.currentTimeMillis());
        try {
            MessageConnectorData builder = MessageConnectorData.builder(MessageUtil.encode(timeNotify), MessageUtil.checkObjType(timeNotify.getClass()).valueInt(), MessageUtil.getCommandId(timeNotify));
            for (Channel<ChannelFuture> channel : channels) {
                ChannelFuture future = channel.send(builder);
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        log.info("Send time to client: {}, isSuccess:{}", channel.getChannelId(), future.isSuccess());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
