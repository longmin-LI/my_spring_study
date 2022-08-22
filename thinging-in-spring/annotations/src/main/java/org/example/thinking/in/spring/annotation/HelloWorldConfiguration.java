package org.example.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auhtor llm
 * @data 2022/8/22 23:56
 */
@Configuration //加不加都可以
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld(){
        return "hello, world";
    }
}
