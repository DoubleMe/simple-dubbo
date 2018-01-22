package com.chenhm.rpc.handler;

import com.chenhm.rpc.channel.Channel;
import com.chenhm.rpc.channel.NettyChannelAdapter;
import com.chenhm.rpc.future.ResponseFuture;
import com.chenhm.rpc.rpc.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chen-hongmin
 * @since 2018/1/10 15:38
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    private NettyChannelAdapter channelAdapter;


    /**
     * Creates a client-side handler.
     */
    public NettyClientHandler(NettyChannelAdapter channelAdapter) {
        this.channelAdapter = channelAdapter;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        channelAdapter.getOrAddChannel(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof Response){

            Channel channel = channelAdapter.getOrAddChannel(ctx);
            ResponseFuture.received(channel,(Response)msg);
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


}
