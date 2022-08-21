package org.example.thinking.in.spring.bean.factory;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    //增加的实现方式
    //1.基于@PostContruct注解，他是Java注解,1.6开始就有了
    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct进行初始化：" + "UserFactory初始化中...");
    }
    //2.自定义初始化方法
    public void initUserFactory(){
        System.out.println("自定义初始化方法 initUserFactory :" + "UserFactory初始化中...");
    }
    //这个方法是InitializingBean这个接口中的，必须要实现，属于属性被设置之后的实现
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean中的afterPropertiesSet :" + "UserFactory初始化中...");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("@preDestroy : UserFactory Bean销毁中");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean中的destroy方法 : UserFactory Bean销毁中");
    }
    public void doDestroy(){
        System.out.println("自定义的销毁方法doDestroy : UserFactory Bean销毁中");
    }
}
