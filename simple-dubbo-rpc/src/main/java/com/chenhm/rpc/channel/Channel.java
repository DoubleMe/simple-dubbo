package com.chenhm.rpc.channel;

import com.chenhm.base.exception.RpcException;
import com.chenhm.rpc.future.Future;
import com.chenhm.rpc.rpc.Request;
import com.chenhm.rpc.rpc.Response;

/**
 * @author chen-hongmin
 * @since 2018/1/16 10:58
 */
public interface Channel {

    /**
     * 异步发送消息
     * @param request request
     * @throws RpcException
     */
    Future send(Request request) throws RpcException;

    /**
     * 异步发送消息
     * @param request request
     * @param timeout 超时时间
     * @throws RpcException
     */
    Future send(Request request, Long timeout) throws RpcException;


    /**
     * 异步回复消息
     * @param response response
     * @throws RpcException
     */
    void reply(Response response) throws RpcException;

    /**
     * 是否连接
     * @return true or false
     */
    boolean isConnected();

    /**
     * close the channel.
     */
    void close();
}
