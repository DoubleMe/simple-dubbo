package com.chenhm.test;

import com.chenhm.config.config.ProviderConfig;
import com.chenhm.test.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chen-hongmin
 * @since 2018/1/5 9:52
 */

public class ProviderTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/spring-provider.xml"});
        context.start();
    }

    @Test
    public void providerTest() throws Exception{
        ProviderConfig test = (ProviderConfig) context.getBean("com.chenhm.test.service.DemoService");

        DemoService demoService = (DemoService)test.getRef();
        demoService.sendMessage("hello world");

        // 按任意键退出
        System.in.read();
    }
}
