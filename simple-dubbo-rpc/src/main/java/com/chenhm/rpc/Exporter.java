package com.chenhm.rpc;

import com.chenhm.base.URL;
import com.chenhm.base.constant.BizConstants;
import com.chenhm.base.util.StringUtils;
import com.chenhm.rpc.invoker.Invoker;

import java.util.List;

/**
 * @author chen-hongmin
 * @since 2018/1/5 16:29
 */
public class Exporter<T> {

    /**
     * 应用名称
     */
    private String application;

    /**
     * 地址
     */
    private String address;

    /**
     * 角色
     */
    private String role;

    /**
     * 版本
     */
    private String version;

    /**
     * 组
     */
    private String group;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法集合
     */
    private List<String> methods;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 注册地址url
     */
    private URL url;

    private Invoker<T> invoker;

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Invoker<T> getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker<T> invoker) {
        this.invoker = invoker;
    }

    public String getPath(){

        StringBuffer sb = new StringBuffer(BizConstants.ROOT);
        sb.append("/").append(interfaceName).append("/").append(role);
        sb.append("/").append("application=").append(application)
                .append("&").append("version=").append(version)
                .append("&").append("interface=").append(interfaceName)
                .append("&").append("group=").append(group)
                .append("&").append("methods=").append(StringUtils.join(methods,","))
                .append("&").append("address=").append(address);


        return sb.toString();
    }
}
