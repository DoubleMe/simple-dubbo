package com.chenhm.config.config;

import com.chenhm.base.context.SpringContextUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author chen-hongmin
 * @since 2018/1/4 17:48
 */
public abstract class AbstractConfig implements InitializingBean,ApplicationContextAware,ApplicationListener<ContextStartedEvent> {

    /**
     * 注册信息
     */
    private RegistryConfig registryConfig;

    /**
     * 应用信息
     */
    private ApplicationConfig applicationConfig;

    /**
     * 应用协议
     */
    private ProtocolConfig protocolConfig;

    private ApplicationContext applicationContext;

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    public void setApplicationConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public ProtocolConfig getProtocolConfig() {
        return protocolConfig;
    }

    public void setProtocolConfig(ProtocolConfig protocolConfig) {
        this.protocolConfig = protocolConfig;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        SpringContextUtils.setApplicationContext(applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (getRegistryConfig() == null) {
            RegistryConfig registryConfig = getBean(RegistryConfig.class);
            setRegistryConfig(registryConfig);
        }
        if (getApplicationConfig() == null) {
            ApplicationConfig applicationConfig = getBean(ApplicationConfig.class);
            setApplicationConfig(applicationConfig);
        }
        if(getProtocolConfig() == null){
            ProtocolConfig protocolConfig = getBean(ProtocolConfig.class);
            setProtocolConfig(protocolConfig);
        }
    }

    /**
     *
     * 通过名称获取bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){

        Object bean = applicationContext.getBean(beanName);

        return bean;
    }

    /**
     * 通过类型获取
     * @param requiredType
     * @param <T>
     * @return
     */
    public  <T> T getBean(Class<T> requiredType){

        T t = applicationContext.getBean(requiredType);

        return t;
    }
}
