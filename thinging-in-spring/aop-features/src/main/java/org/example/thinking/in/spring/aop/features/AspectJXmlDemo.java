package org.example.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@Aspect
public class AspectJXmlDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/spring-aop-context.xml");

//        applicationContext.getBean(AspectJXmlDemo.class);

        applicationContext.close();
    }
}
