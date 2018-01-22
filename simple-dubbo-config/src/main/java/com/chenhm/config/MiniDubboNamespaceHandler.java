package com.chenhm.config;

import com.chenhm.config.config.*;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author chen-hongmin
 * @since 2017/12/29 15:37
 */
public class MiniDubboNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("provider", new MiniDubboBeanDefinitionParser(ProviderConfig.class));
        registerBeanDefinitionParser("registry", new MiniDubboBeanDefinitionParser(RegistryConfig.class));
        registerBeanDefinitionParser("application", new MiniDubboBeanDefinitionParser(ApplicationConfig.class));
        registerBeanDefinitionParser("reference", new MiniDubboBeanDefinitionParser(ReferenceConfig.class));
        registerBeanDefinitionParser("protocol", new MiniDubboBeanDefinitionParser(ProtocolConfig.class));
    }
}
