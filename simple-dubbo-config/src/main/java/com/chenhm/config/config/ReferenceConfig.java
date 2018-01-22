package com.chenhm.config.config;

import com.chenhm.base.URL;
import com.chenhm.base.UrlKeys;
import com.chenhm.rpc.invoker.Invoker;
import com.chenhm.rpc.invoker.impl.RpcInvoker;
import com.chenhm.rpc.proxy.JavassistProxy;
import com.chenhm.rpc.proxy.ProxyFactory;
import com.chenhm.base.util.UrlUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.event.ContextStartedEvent;

import java.util.HashMap;

/**
 * @author chen-hongmin
 * @since 2018/1/6 10:04
 */
public class ReferenceConfig<T> extends AbstractConfig implements FactoryBean {


    /**
     * 接口名称
     */
    private String interfaceName;


    /**
     * 接口类型
     */
    private Class interfaceClass;


    /**
     * 接口代理类引用
     */
    private transient volatile T ref;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }


    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    @Override
    public Object getObject() throws Exception {

        init();
        return ref;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private void init() {

        try {
            interfaceClass = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        ProxyFactory proxyFactory = new JavassistProxy();
        ref = (T)proxyFactory.getProxy(getInvoker(),interfaceClass);

    }

    private Invoker<T> getInvoker() {

        HashMap<String, String> map = new HashMap<>();
        map.put(UrlKeys.VERSION,getRegistryConfig().getVersion());
        map.put(UrlKeys.GROUP,getRegistryConfig().getGroup());

        Invoker invoker = new RpcInvoker(interfaceClass, getUrl(), map);
        return invoker;
    }

    /**
     * 获取URL
     *
     * @return
     */
    private URL getUrl() {

        String protocol = UrlUtils.getProtocol(getRegistryConfig().getAddress());
        String address = UrlUtils.getAddress(getRegistryConfig().getAddress());
        URL url = new URL(protocol, address);

        return url;
    }
}
