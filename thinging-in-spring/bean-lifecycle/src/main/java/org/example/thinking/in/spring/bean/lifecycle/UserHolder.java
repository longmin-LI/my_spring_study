package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;

    private Integer number;

    private String desc;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDesc() {
        return desc;
    }

    public UserHolder(User user){
        this.user = user;
    }
    /*
    这个操作依赖于注解驱动：当前场景是BeanFactory
     */
    @PostConstruct
    public void initPostConstruct(){
        this.desc = "the user holder V4";
        System.out.println("initPostConstruct(): " + desc);
    }

    public void init(){
        this.desc = "the user holder v6";
        System.out.println("init(): " + desc);
    }

    @PreDestroy
    public void preDestroy(){
        this.desc = "the user holder v10";
        System.out.println("preDestroy(): " + desc);
    }

    public void doDestroy(){
        this.desc = "the user holder v12";
        System.out.println("doDestroy(): " + desc);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("aware接口被回调");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.desc = "the user holder v5";
        System.out.println("afterPropertiesSet(): " + desc);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.desc = "the user holder v8";
        System.out.println("afterSingletonsInstantiated(): " + desc);
    }

    @Override
    public void destroy() throws Exception {
        this.desc = "the user holder v11";
        System.out.println("destroy(): " + desc);
    }
}
