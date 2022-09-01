package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于XML实现 point cut配置
 */
@Configuration //Configuration Class 他会被cglib提升
public class AspectJSchemaBasedPointcutDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/spring-aop-context.xml");

        applicationContext.refresh();

        EchoService bean = applicationContext.getBean("echoService", EchoService.class);

        System.out.println(bean.echo("hello, world"));

        applicationContext.close();

    }

    public void execute(){
        System.out.println("方法执行中...");
    }
}
