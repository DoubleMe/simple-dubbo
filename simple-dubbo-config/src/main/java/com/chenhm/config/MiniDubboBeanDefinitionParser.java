package com.chenhm.config;

import com.chenhm.config.config.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author chen-hongmin
 * @since 2017/12/29 15:38
 */
public class MiniDubboBeanDefinitionParser implements BeanDefinitionParser {

    /**
     * beanClass
     */
    private final Class<?> beanClass;

    public MiniDubboBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        //创建beanDefinition
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        String id = element.getAttribute("id");
        String interfaceName = element.getAttribute("interface");
        if (id == null || id.length() == 0){
            id = interfaceName;
        }

        if(id == null || id.length() == 0){
            id = beanClass.getName();
        }

        //在spring上 注册这个bean
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        if (ProviderConfig.class.equals(beanClass)){
            String ref = element.getAttribute("ref");
            //填充bean 数据
            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", interfaceName);
            //ref 应用类型 填充RuntimeBeanReference
            beanDefinition.getPropertyValues().addPropertyValue("ref", new RuntimeBeanReference(ref));
        }else if (RegistryConfig.class.equals(beanClass)){
            String address = element.getAttribute("address");
            String group = element.getAttribute("group");
            String check = element.getAttribute("check");
            String version = element.getAttribute("version");
            beanDefinition.getPropertyValues().addPropertyValue("address", address);
            beanDefinition.getPropertyValues().addPropertyValue("group", group);
            beanDefinition.getPropertyValues().addPropertyValue("check", check);
            beanDefinition.getPropertyValues().addPropertyValue("version", version);
        }else if (ApplicationConfig.class.equals(beanClass)){
            String name = element.getAttribute("applicationName");
            beanDefinition.getPropertyValues().addPropertyValue("applicationName", name);
        }else if (ReferenceConfig.class.equals(beanClass)){
            beanDefinition.getPropertyValues().addPropertyValue("interfaceName", interfaceName);
        }else if (ProtocolConfig.class.equals(beanClass)){
            String name = element.getAttribute("name");
            String port = element.getAttribute("port");
            beanDefinition.getPropertyValues().addPropertyValue("name", name);
            beanDefinition.getPropertyValues().addPropertyValue("port", port);
        }


        return beanDefinition;
    }

}
