package org.example.thinking.in.spring.aop.features;

import org.aopalliance.aop.Advice;
import org.example.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.example.thinking.in.spring.aop.overview.DefaultEchoService;
import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyCreatorSupport;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @auhtor llm
 * @data 2022/8/31 16:07
 */
public class ProxyCreatorSupportDemo {

    public static void main(String[] args) {

        DefaultEchoService defaultEchoService = new DefaultEchoService();
        //注入目标对象
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTargetClass(DefaultEchoService.class);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP 配置对象" + advised + "已激活");
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP 配置对象" + advised + "已变化");

            }
        });
        //触发激活时间，createAopProxy() <- getProxy
        EchoService proxy = (EchoService) proxyFactory.getProxy();
//        System.out.println(proxy.echo("caonima"));
        //添加
        proxyFactory.addAdvice(new Advice() {
        });
    }
}
