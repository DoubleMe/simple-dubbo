package com.chenhm.rpc.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;

/**
 * @author chen-hongmin
 * @since 2018/1/10 17:17
 */
public class DubboChannelFutureListener implements ChannelFutureListener {

    private EventLoopGroup workerGroup;

    public DubboChannelFutureListener(EventLoopGroup workerGroup) {

        this.workerGroup = workerGroup;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {

        if (future.isSuccess()) {
//            workerGroup.shutdownGracefully();
        }
    }
}
