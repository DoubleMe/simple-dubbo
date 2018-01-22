package com.chenhm.registy.factory;

import com.chenhm.base.util.UrlUtils;
import com.chenhm.rpc.BaseCacheContext;
import com.chenhm.registy.zookeeper.ZooClient;
import com.chenhm.rpc.Exporter;
import com.chenhm.rpc.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:34
 */
public class ZKRegisty implements Registy {


    private static final Logger log = LoggerFactory.getLogger("zookeeper");

    private ZooClient zooClient;

    private Exporter exporter;

    public ZKRegisty(Exporter exporter) {
        this.zooClient = new ZooClient(exporter.getUrl());
        this.exporter = exporter;
    }

    @Override
    public void register() {
        String path = exporter.getPath();

        String[] pathArr = path.split("/");
        String zkPath = "";
        for (String child : pathArr) {
            if (child.length() == 0) {
                continue;
            }
            zkPath += "/";
            zkPath += child;
            zooClient.create(zkPath, false);
        }
        log.info("zookeeper path = {} register successful", zkPath);
        BaseCacheContext.addExporter(exporter);
        openServer();
    }


    private void openServer() {

        int port = UrlUtils.getPort(exporter.getAddress());
        NettyServer server = new NettyServer(port);

        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
