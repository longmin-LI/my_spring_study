package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author lilongmin
 * @date 2022/8/23 13:53
 * @description
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ProfileDemo.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd"); //默认的就是一个兜底的

        //还可以增加活跃的，活跃的优先
        environment.setActiveProfiles("even");
        context.refresh();

        Integer bean = context.getBean("number", Integer.class);
        System.out.println(bean);

        context.close();
    }
    @Bean(name = "number")
    @Profile("odd")
    public Integer odd(){
        return 1;
    }
    @Bean(name = "number")
//    @Profile("even")
    @Conditional(EvenCondition.class)
    public Integer even(){
        return 2;
    }

}
