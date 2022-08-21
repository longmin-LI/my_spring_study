package org.example.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopInterceptorDemo {

    public static void main(String[] args) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    //这个就是前置before做的一个拦截的动作，我们可以将其抽象出来
//                    long startTime = System.currentTimeMillis();
//                    try {
//                        EchoService echoService = new DefaultEchoService();
//                        return echoService.echo((String) args[0]);
//                    } finally {
//                        long costTime = System.currentTimeMillis() - startTime;
//                        System.out.println("echo 方法的执行时间为： " + costTime + "ms,");
//                    }
                      //用抽象出来的逻辑
                    BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                        @Override
                        public Object before(Object proxy, Method method, Object[] args) {
                            return System.currentTimeMillis();
                        }
                    };
                    long startTime = (long) beforeInterceptor.before(proxy,method,args);
                    EchoService echoService = new DefaultEchoService();
                    String res = echoService.echo((String) args[0]);
                    //后置
                    AfterInterceptor afterInterceptor = new AfterInterceptor() {
                        @Override
                        public Object after(Object proxy, Method method, Object[] args, Object returnResult) {
                            return System.currentTimeMillis();
                        }
                    };
                    //执行after
                    long endTime = (long) afterInterceptor.after(proxy, method, args, res);
                    long costTime = endTime - startTime;
                    System.out.println(costTime);
                    return res;
                }
                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        String rinima = ((EchoService) proxy).echo("rinima");
        System.out.println(rinima);
    }
}
