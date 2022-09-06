package org.example.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionParseDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //结论1：AnnotatedBeanDefinitionReader并没有实现BeanDefinitionReader这个接口，他是单独独立的
        //他的bean名称是由AnnotationBeanNameGenerator来生成的，也可以自定义，但是需要自己去实现BeanNameGenerator接口
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionsBefore = beanFactory.getBeanDefinitionCount();
        //注意这个注册的bean并不一定要被Component或者Configuration注解所标注
        beanDefinitionReader.register(AnnotatedBeanDefinitionParseDemo.class);
        int beanDefinitionAfter = beanFactory.getBeanDefinitionCount();
        System.out.println(beanDefinitionAfter - beanDefinitionsBefore);
        System.out.println(beanFactory.getBean("annotatedBeanDefinitionParseDemo", AnnotatedBeanDefinitionParseDemo.class));
    }
}
