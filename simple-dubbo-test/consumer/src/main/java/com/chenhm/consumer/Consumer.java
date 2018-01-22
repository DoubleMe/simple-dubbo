package com.chenhm.consumer;

import com.chenhm.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chen-hongmin
 * @since 2018/1/22 16:03
 */
public class Consumer {

    public static void main(String[] args)  throws Exception{
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"classpath:spring/spring-consumer.xml"});
        context.start();

        DemoService demoService = (DemoService)context.getBean("demoService");
        String ret = demoService.sendMessage("hello dubbo");

        System.out.println(ret);

    }
}
