package org.example.thinking.in.spring.ioc.overview.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.annotation.Super;
import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        //配置XML配置文件
        //启动应用上下文

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");

        //实时加载
        lookupInRealTime(beanFactory);
        //延时加载
        lookupInLazy(beanFactory);
        //按照类型查找
        lookupByType(beanFactory);
        //按照类型查找集合对象
        lookupCollectionByType(beanFactory);
        //根据注解来查找
        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("获取加了@Super注解的所有User集合对象" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory factory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = factory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象" + beans);
        }
    }

    public static void lookupInLazy(BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactoryCreatingFactoryBean");
        System.out.println(objectFactory.getClass());
        User user = objectFactory.getObject();
        System.out.println("延迟查找" + user);
    }
    public static void lookupInRealTime(BeanFactory beanFactory){
        //这个就是实时查找
        User user = (User) beanFactory.getBean("user");

        System.out.println(user);
    }
    public static void lookupByType(BeanFactory beanFactory){
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找" + user);
    }

}
