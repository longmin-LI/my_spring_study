package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auhtor llm
 * @data 2022/8/22 23:54
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EnableModuleDemo.class);

        context.refresh();

        String bean = context.getBean(String.class);

        System.out.println(bean);

        context.close();

    }
}
