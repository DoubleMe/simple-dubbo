package com.chenhm.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen-hongmin
 * @since 2018/1/4 18:04
 */
public class URL implements Serializable {

    /**
     * 通信协议
     */
    private String protocol;

    /**
     * zk服务器地址
     */
    private String host;

    /**
     * 参数
     */
    private Map<String, String> parameters;


    public URL(String protocol, String host) {
        this(protocol, host, null);
    }

    public URL(String protocol, String host, Map<String, String> parameters) {
        this.protocol = protocol;
        this.host = host;
        this.parameters = parameters == null ? new HashMap<>() : parameters;
    }


    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void addParameters(Map<String, String> parameters) {

        parameters.putAll(parameters);
    }

    public void addParameter(String key, String value) {

        parameters.put(key, value);
    }

    public String getParameter(String key) {

        return getParameter(key,null);
    }

    public String getParameter(String key, String defaultValue) {

        if (parameters == null || !parameters.containsKey(key)) {
            return defaultValue;
        }
        String value = parameters.get(key);

        return value;
    }


}
