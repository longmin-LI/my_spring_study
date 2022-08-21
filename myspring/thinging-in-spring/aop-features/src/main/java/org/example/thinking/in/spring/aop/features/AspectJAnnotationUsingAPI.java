package org.example.thinking.in.spring.aop.features;

import org.example.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AspectJAnnotationUsingAPI {

    public static void main(String[] args) {
        //通过创建一个hashMap缓存，作为被代理的对象
        Map<String,Object> cache = new HashMap<>();
        //创建Proxy工厂
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        //增加配置类
        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if("put".equals(method.getName()) && args.length == 2){
                    System.out.printf("当前存放的是Key: %s, value：%s %n", args[0], args[1]);
                }
            }
        });
        //错误的调用
//        cache.put("1","A");
        //正确的调用，应该用代理对象
        Map<String,Object> proxy = proxyFactory.getProxy();
        proxy.put("1","A");
        System.out.println(cache.get("1"));
    }
}
