package com.chenhm.config.config;

/**
 * @author chen-hongmin
 * @since 2018/1/4 17:40
 */
public class RegistryConfig {

    /**
     * 注册地址
     */
    private String address;

    /**
     * 注册协议 zookeeper 或者redis
     */
    private String protocol;

    /**
     * 是否启动检查
     */
    private String check;

    /**
     * 组
     */
    private String group;

    /**
     * 版本
     */
    private String version;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
