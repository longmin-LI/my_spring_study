package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.example.thinking.in.spring.aop.overview.DefaultEchoService;
import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @auhtor llm
 * @data 2022/8/26 9:28
 */
public class ProxyFactoryDemo {

    public static void main(String[] args) {

        DefaultEchoService echoService = new DefaultEchoService();

        ProxyFactory factory = new ProxyFactory(echoService);
        //增加Advice实现：MethodInterceptor 继承于 Interceptor -> advice
        factory.addAdvice(new EchoServiceMethodInterceptor());
        //获取代理对象
        EchoService proxy = (EchoService) factory.getProxy();

        System.out.println(proxy.echo("hello, world"));

    }
}
