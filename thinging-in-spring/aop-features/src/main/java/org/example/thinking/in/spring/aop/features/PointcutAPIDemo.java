package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.example.thinking.in.spring.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import org.example.thinking.in.spring.aop.features.pointcut.EchoServicePointcut;
import org.example.thinking.in.spring.aop.overview.DefaultEchoService;
import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @auhtor llm
 * @data 2022/8/26 10:18
 */
public class PointcutAPIDemo {

    public static void main(String[] args) {

//        EchoServicePointcut pointcut = new EchoServicePointcut("echo", EchoService.class);
//        EchoServiceEchoMethodPointcut pointcut = EchoServiceEchoMethodPointcut.INSTANCE;
        ComposablePointcut pointcut = new ComposablePointcut(EchoServiceEchoMethodPointcut.INSTANCE);

        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        //组合实现
        pointcut.intersection(echoServicePointcut.getClassFilter());
        pointcut.intersection(echoServicePointcut.getMethodMatcher());



        //将Pointcut 适配成 Advisor
        //Advisor就是连接Advice和pointcut的一个桥梁
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

        DefaultEchoService echoService = new DefaultEchoService();

        ProxyFactory factory = new ProxyFactory(echoService);
        //添加Advisor
        factory.addAdvisor(advisor);
        //获取代理对象
        EchoService proxy = (EchoService) factory.getProxy();

        System.out.println(proxy.echo("caonima"));


    }
}
