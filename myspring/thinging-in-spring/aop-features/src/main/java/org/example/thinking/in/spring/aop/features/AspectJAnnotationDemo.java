package org.example.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect //声明一个切面
@Configuration //Configuration Class 他会被cglib提升
@EnableAspectJAutoProxy //激活Aspect注解自动代理能力
public class AspectJAnnotationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotationDemo.class);

        applicationContext.refresh();

        AspectJAnnotationDemo bean = applicationContext.getBean(AspectJAnnotationDemo.class);
        System.out.println(bean);

        applicationContext.close();
    }
}
