package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auhtor llm
 * @data 2022/8/26 14:47
 */
public class AspectJSchemaBasedAutoProxyDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-aop-auto-proxy.xml");

        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);

        System.out.println(echoService.echo("caonima"));

        context.close();
    }
}
