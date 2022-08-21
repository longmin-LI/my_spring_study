package org.example.thinking.in.spring.aop.features.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @auhtor llm
 * @data 2022/8/15 1:03
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("拦截了EchoService的方法：" + method);
        //如果这里返回null，那么真实执行的方法也会返回一个空
        return invocation.proceed();
    }
}
