package org.example.thinking.in.spring.ioc.overview.container;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 注解能力的ApplicationContext作为IoC容器
 */
//注意这个注解一定要加上，不然就不算是配置类
@Configuration
public class AnnotationApplicationContextDemo {

    public static void main(String[] args) {
        //1.创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类作为配置类（Configuration class）
        applicationContext.register(AnnotationApplicationContextDemo.class);
        //启动应用上下文,不加会报错org.springframework.context.annotation.AnnotationConfigApplicationContext@37f8bb67 has not been refreshed yet
        applicationContext.refresh();
        //依赖查找集合对象
        lookupCollectionByType(applicationContext);
    }
    //通过java 注解的方式定义了一个bean
    @Bean
    public User user(){
        User user = new User();
        user.setId(2L);
        user.setName("llm");
        return user;
    }
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory factory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = factory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象" + beans);
        }
    }

}
