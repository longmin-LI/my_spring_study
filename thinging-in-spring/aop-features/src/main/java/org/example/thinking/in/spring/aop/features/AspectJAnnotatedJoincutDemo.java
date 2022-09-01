package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //Configuration Class 他会被cglib提升
@EnableAspectJAutoProxy //激活Aspect注解自动代理能力
public class AspectJAnnotatedJoincutDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AspectJAnnotatedJoincutDemo.class, AspectConfiguration.class);

        applicationContext.refresh();

        AspectJAnnotatedJoincutDemo bean = applicationContext.getBean(AspectJAnnotatedJoincutDemo.class);

        bean.execute();

        applicationContext.close();
    }

    public void execute(){
        System.out.println("方法执行中...");
    }
}
