package org.example.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author lilongmin
 * @date 2022/8/23 15:54
 * @description: 注入Environment
 */

public class InjectingEnvironment implements EnvironmentAware {

    private Environment environment;

    @Autowired
    private Environment environment2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectingEnvironment.class);

        context.refresh();
        //下面两个都是true
        InjectingEnvironment bean = context.getBean(InjectingEnvironment.class);
        System.out.println(bean.environment == bean.environment2);
        System.out.println(bean.environment == context.getEnvironment());
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
