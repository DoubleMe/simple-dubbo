package com.chenhm.base.context;

import org.springframework.context.ApplicationContext;

/**
 * @author chen-hongmin
 * @since 2018/1/12 15:05
 */
public class SpringContextUtils {

    private static ApplicationContext _applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        if (_applicationContext == null){
            _applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){

        return _applicationContext;
    }

    public static Object getBean(String beanName){

        return _applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanClass){

        return _applicationContext.getBean(beanClass);
    }


}
