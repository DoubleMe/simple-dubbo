package com.chenhm.config.config;

/**
 * @author chen-hongmin
 * @since 2018/1/6 14:13
 */
public class ProtocolConfig {

    /**
     * 协议名称
     */
    private String name;

    /**
     * 端口
     */
    private String port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
