package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

@Configuration
public class BeanInitializationDemo{

    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(BeanInitializationDemo.class);
        //启动Spring应用上下文
        applicationContext.refresh();
        //非延迟吃实话再Spring应用上下文启动完成后被初始化
        System.out.println("Spring应用上下文启动....");
        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println("应用上下文准备关闭");
        //关闭Spring应用上下文
        applicationContext.close();
        System.out.println("应用上下文关闭");
    }
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = true)
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
