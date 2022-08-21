package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册实例
 *
 */
public class SingletonBeanRegistryDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册外部单例对象userFactory
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //注册，ConfigurableListableBeanFactory这个接口非常重要
        beanFactory.registerSingleton("userFactory",userFactory);
        //启动Spring应用上下文
        applicationContext.refresh();
        //依赖查找
        UserFactory bean = beanFactory.getBean("userFactory", UserFactory.class);
        //返回的结果是true
        System.out.println(bean == userFactory);
        //关闭Spring应用上下文
        applicationContext.close();

    }
}
