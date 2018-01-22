package com.chenhm.rpc.client;

import com.chenhm.base.URL;
import com.chenhm.base.UrlKeys;
import com.chenhm.base.util.UrlUtils;
import com.chenhm.registy.zookeeper.ZooClient;
import com.chenhm.rpc.channel.NettyChannelAdapter;

import java.util.List;

/**
 * @author chen-hongmin
 * @since 2018/1/11 10:24
 */
public abstract class AbstractClient extends NettyChannelAdapter implements Client {


    /**
     * url
     */
    protected URL url;

    /**
     * 是否连接
     */
    protected boolean isConnect;

    protected String interfaceName;


    protected String host;

    protected int port;


    protected String address;


    private long timeout = 100000;

    public AbstractClient(URL url) {
        this.url = url;
        init();
    }

    private void init(){

        URL server = findServer();
        if (server == null){
            throw new RuntimeException();
        }

        this.interfaceName = url.getParameter(UrlKeys.INTERFACE);
        this.address = server.getParameter(UrlKeys.ADDRESS);
        this.host = UrlUtils.getIp(address);
        this.port = UrlUtils.getPort(address);
    }

    @Override
    public void open() {

        try {
            doOpen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }


    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public URL findServer() {

        ZooClient zooClient = new ZooClient(url);

        List<URL> servers = zooClient.findServers();
        if (servers.isEmpty()) {
            return null;
        }

        return servers.get(0);
    }

    /**
     * do open
     * @throws InterruptedException
     * @return Channel
     */
    protected abstract void doOpen() throws InterruptedException;
}
