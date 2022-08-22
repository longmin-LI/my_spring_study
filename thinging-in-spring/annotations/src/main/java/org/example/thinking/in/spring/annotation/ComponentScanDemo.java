package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link org.springframework.context.annotation.ComponentScan}
 * @auhtor llm
 * @data 2022/8/22 22:26
 */
//@ComponentScan(basePackages = "org.example.thinking.in.spring.annotation")
@MyComponentScan(scanBasePackages = "org.example.thinking.in.spring.annotation")
public class ComponentScanDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ComponentScanDemo.class);

        context.refresh();

        TestClass bean = context.getBean(TestClass.class);
        System.out.println(bean);

        context.close();

    }
}
