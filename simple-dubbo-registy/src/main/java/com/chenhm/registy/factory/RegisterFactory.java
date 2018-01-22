package com.chenhm.registy.factory;


import com.chenhm.rpc.Exporter;

/**
 * @author chen-hongmin
 * @since 2018/1/5 15:11
 */
public class RegisterFactory {

    private static final String ZOOKEEPER = "zookeeper";

    public static Registy getRegisty(Exporter exporter) {

        if (ZOOKEEPER.equals(exporter.getUrl().getProtocol())) {
            return new ZKRegisty(exporter);
        }
        return new ZKRegisty(exporter);
    }
}
