package com.chenhm.test;

import com.chenhm.test.service.DemoService;
import com.chenhm.test.service.dto.MessageDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chen-hongmin
 * @since 2018/1/11 15:53
 */
public class ConsumerTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/spring-consumer.xml"});
        context.start();
    }

    @Test
    public void consumerTest() throws Exception{
        DemoService demoService = (DemoService) context.getBean("demoService");

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId("s");
        messageDTO.setName("ss");
        messageDTO.setMessage("message");
        String message = demoService.sendMessage(messageDTO);

        System.out.println(message);
    }
}
