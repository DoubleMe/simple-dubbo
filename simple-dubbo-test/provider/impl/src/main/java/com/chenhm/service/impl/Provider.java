package com.chenhm.service.impl;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chen-hongmin
 * @since 2018/1/5 9:52
 */

public class Provider {


    public static void main(String[] args)  throws Exception{
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"classpath:spring/spring-provider.xml"});
        context.start();

        // 按任意键退出
        System.in.read();
    }

}
