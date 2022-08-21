package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-definition-context.xml");
        //通过别名来获取bean
        User longGe = beanFactory.getBean("long-ge", User.class);
        User user = beanFactory.getBean("user",User.class);
        //说明底层肯定做了一个名称和别名的一个映射
        System.out.println(user == longGe);
    }

}
