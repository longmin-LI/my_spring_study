package org.example.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lilongmin
 * @date 2022/8/23 16:11
 * @description
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String name;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ValueAnnotationDemo.class);

        context.refresh();

        ValueAnnotationDemo bean = context.getBean(ValueAnnotationDemo.class);
        System.out.println(bean.name);
        context.close();
    }
}
