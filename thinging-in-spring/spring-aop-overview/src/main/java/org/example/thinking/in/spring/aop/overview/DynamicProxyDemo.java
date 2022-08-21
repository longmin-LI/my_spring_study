package org.example.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        //需要传三个参数进去，第一个就是classloader类加载器，这里的类加载器就是新生成的类的加载器，因为动态代码会生成新的类，
        //需要把这个新的类转载到这个classloader里面去，并且最好和当前线程关联的classloader保持一致
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    EchoService echoService = new EchoServiceProxy(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        String rinima = ((EchoService) proxy).echo("rinima");
        System.out.println(rinima);

        Object proxy1 = Proxy.newProxyInstance(classLoader, new Class[]{Comparable.class}, (proxy2, method, args1) -> {
            return null;
        });
        System.out.println(proxy1.getClass());
    }
}
