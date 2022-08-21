package org.example.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo {
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CommonAnnotationBeanPostProcessor annotationBeanPostProcessor;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initByInject(){
        System.out.println((applicationContext == beanFactory));
        System.out.println((applicationContext == resourceLoader));
        System.out.println( (applicationContext == applicationEventPublisher));
        System.out.println( (applicationContext.getAutowireCapableBeanFactory() == beanFactory));
        System.out.println(annotationBeanPostProcessor);
    }

    @PostConstruct
    public void initByLookup(){
        getBean(beanFactory.getClass());
        getBean(resourceLoader.getClass());
        getBean(applicationContext.getClass());
        getBean(applicationEventPublisher.getClass());
        getBean(annotationBeanPostProcessor.getClass());
    }
    public <T> T getBean(Class<T> beanType){
        try {
            T bean = beanFactory.getBean(beanType);
            System.out.println(bean);
        }catch (NoSuchBeanDefinitionException e){
            System.err.println("找不到" + beanType);
        }
        return null;
    }

    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(DependencySourceDemo.class);
        //启动
        applicationContext.refresh();
        //关闭
        applicationContext.close();
    }
}
