package com.chenhm.config.config;


import com.chenhm.base.URL;
import com.chenhm.base.constant.BizConstants;
import com.chenhm.rpc.Exporter;
import com.chenhm.rpc.invoker.Invoker;
import com.chenhm.rpc.proxy.JavassistProxy;
import com.chenhm.rpc.proxy.ProxyFactory;
import com.chenhm.base.util.NetUtils;
import com.chenhm.base.util.UrlUtils;
import com.chenhm.registy.factory.RegisterFactory;
import org.springframework.context.event.ContextStartedEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @since 2017/12/29 15:26
 */
public class ProviderConfig<T> extends AbstractConfig {

    /**
     * service id
     */
    private Class interfaceClass;

    /**
     * service interfaceName
     */
    private String interfaceName;

    /**
     * service ref
     */
    private T ref;

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }


    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

        RegisterFactory.getRegisty(getExporter()).register();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        interfaceClass = Class.forName(interfaceName);
    }

    /**
     * 获取暴露服务对象
     *
     * @return
     */
    public Exporter getExporter() {

        Exporter exporter = new Exporter();
        RegistryConfig registryConfig = getRegistryConfig();
        ApplicationConfig applicationConfig = getApplicationConfig();
        ProtocolConfig protocolConfig = getProtocolConfig();

        exporter.setApplication(applicationConfig.getApplicationName());
        exporter.setAddress(NetUtils.getLocalHost() + ":" + protocolConfig.getPort());
        exporter.setGroup(registryConfig.getGroup());
        exporter.setMethods(getMethods());
        exporter.setRole(BizConstants.PROVIDER);
        exporter.setVersion(registryConfig.getVersion());
        exporter.setInterfaceName(getInterfaceName());
        exporter.setUrl(getUrl());

        ProxyFactory proxyFactory = new JavassistProxy();
        Invoker invoker = proxyFactory.getInvoker(ref, interfaceClass, getUrl());
        exporter.setInvoker(invoker);
        return exporter;
    }

    /**
     * 获取URL
     *
     * @return
     */
    public URL getUrl() {

        String protocol = UrlUtils.getProtocol(getRegistryConfig().getAddress());
        String address = UrlUtils.getAddress(getRegistryConfig().getAddress());
        URL url = new URL(protocol, address);

        return url;
    }

    /**
     * 获取方法集合
     *
     * @return
     */
    public List<String> getMethods() {

        Class<?> aClass = getRef().getClass();
        Method[] methods = aClass.getMethods();

        List<String> list = new ArrayList<>(methods.length);
        for (Method method : methods) {
            list.add(method.getName());
        }

        return list;
    }

}
