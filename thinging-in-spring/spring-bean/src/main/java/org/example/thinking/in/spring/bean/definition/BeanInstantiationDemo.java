package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");

        User bean = beanFactory.getBean("user-by-static-factory", User.class);

        User user = beanFactory.getBean("user-by-factory-bean", User.class);

        User user1 = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(user1);
        System.out.println(user);
        System.out.println(bean);
        System.out.println(bean == user);
        System.out.println(user == user1);
    }
}
