package com.chenhm.rpc.handler;

import com.chenhm.rpc.channel.Channel;
import com.chenhm.rpc.rpc.Response;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author chen-hongmin
 * @since 2018/1/16 14:39
 */
public interface ChannelHandler {

    /**
     * 获取或者添加一个channel
     * @param handlerContext
     * @return
     */
    Channel getOrAddChannel(ChannelHandlerContext handlerContext);

    /**
     * 处理response
     * @param response
     */
    void handleResponse(Response response);
}
