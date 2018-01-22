package com.chenhm.rpc.client;

import com.chenhm.base.URL;

/**
 * @author chen-hongmin
 * @since 2018/1/11 10:17
 */
public interface Client {


    /**
     * 获取URL
     * @return
     */
    URL getUrl();

    /**
     * 是否连接
     * @return
     */
    boolean isConnect();

    /**
     * 关闭客户端
     */
    void close();

    /**
     * 超时时间
     * @return 超时时间
     */
    long getTimeout();


    /**
     * 发现服务
     * @return
     */
    URL findServer();

    /**
     * 开启客户端
     * @throws  InterruptedException
     * @return  Channel
     */
    void open();
}
