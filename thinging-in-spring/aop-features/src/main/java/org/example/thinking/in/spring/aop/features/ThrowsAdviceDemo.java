package org.example.thinking.in.spring.aop.features;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @auhtor llm
 * @data 2022/8/29 14:38
 */
public class ThrowsAdviceDemo {

    public static void main(String[] args) {
        ThrowsAdviceDemo instance = new ThrowsAdviceDemo();

        ProxyFactory proxyFactory = new ProxyFactory(instance);

        proxyFactory.addAdvice(new MyThrowsAdvice());

        ThrowsAdviceDemo proxy = (ThrowsAdviceDemo) proxyFactory.getProxy();
        proxy.execute();
        proxy.execute();


    }
    public void execute(){
        Random random = new Random();

        if(random.nextBoolean()){
            throw new RuntimeException("for purpose");
        }
        System.out.println("Executing...");
    }
}
