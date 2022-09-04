package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.bean.lifecycle.component.TestBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auhtor llm
 * @data 2022/9/1 18:22
 */
@ComponentScan(basePackages = "org.example.thinking.in.spring.bean.lifecycle")
public class ComponentScanTestDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ComponentScanTestDemo.class);

        context.refresh();

        System.out.println(context.getBean(TestBean.class));
    }
}
