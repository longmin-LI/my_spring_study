package org.example.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auhtor llm
 * @data 2022/8/14 23:36
 */
@Aspect
@Configuration
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/META-INF/spring-aop-context.xml");

        EchoService service = applicationContext.getBean("echoServiceProxyFactoryBean", EchoService.class);

        System.out.println(service.echo("hello, world"));
        applicationContext.close();
    }
}
