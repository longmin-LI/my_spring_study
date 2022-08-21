package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"META-INF/dependency-lookup-context.xml","MATE-INF/bean-constructor-dependency-injection.xml"};

        reader.loadBeanDefinitions(locations);

        beanFactory.preInstantiateSingletons();

        System.out.println(beanFactory.getBean("user", User.class));
        System.out.println(beanFactory.getBean("superUser", SuperUser.class));
        System.out.println(beanFactory.getBean("userHolder", UserHolder.class));


    }
}
