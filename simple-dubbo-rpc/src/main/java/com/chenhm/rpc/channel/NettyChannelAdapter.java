package com.chenhm.rpc.channel;

import com.chenhm.base.exception.RpcException;
import com.chenhm.rpc.future.Future;
import com.chenhm.rpc.future.ResponseFuture;
import com.chenhm.rpc.handler.ChannelHandler;
import com.chenhm.rpc.rpc.Request;
import com.chenhm.rpc.rpc.Response;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author chen-hongmin
 * @since 2018/1/16 11:05
 */
public class NettyChannelAdapter implements Channel, ChannelHandler {

    private ChannelHandlerContext handler;

    /**
     * 是否关闭
     */
    private boolean isClosed;

    /**
     * 是否连接
     */
    private boolean isConnneted;

    /**
     * 接收到的对象
     */
    private Response received;

    @Override
    public Future send(Request request) throws RpcException {

        return send(request, 0L);
    }

    @Override
    public Future send(Request request, Long timeout) throws RpcException {

        handler.writeAndFlush(request);
        return new ResponseFuture(this,timeout);
    }

    @Override
    public void reply(Response response) throws RpcException {
        handler.writeAndFlush(response);
    }

    @Override
    public boolean isConnected() {
        return isConnneted;
    }

    @Override
    public Channel getOrAddChannel(ChannelHandlerContext handlerContext) {
        this.handler = handlerContext;
        return this;
    }

    @Override
    public void handleResponse(Response response) {

        received = response;
    }

    @Override
    public void close() {

        if (isClosed) {
            throw new RuntimeException("fail to close " + this + "channel,because is closed");
        }
        handler.close();
        isClosed = true;
    }
}
