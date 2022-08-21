package org.example.thinking.in.spring.aop.overview;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //指定superclass
        Class<?> superClass = DefaultEchoService.class;
        enhancer.setSuperclass(superClass);
        //指定拦截接口
        enhancer.setInterfaces(new Class[]{EchoService.class});
        //下面这个MethodInterceptor和我们用jdk动态代理的InvocationHandler很像
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long startTime = System.currentTimeMillis();
                //错误使用，因为source已经是被cglib提升过的类，他的全类名为DefaultEchoService$$EnhancerByCGLIB$$faee236a，并且他的父类为DefaultEchoService
                //而我们的目标是要调用DefaultEchoService
               // Object result = method.invoke(source,args);
                //正确的使用为
                Object result = methodProxy.invokeSuper(source, args);
                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("方法执行的时间为： " + costTime);
                return result;
            }
        });
        //生成代理对象
        EchoService echoService = (EchoService) enhancer.create();
        //他的执行时间会比静态代理慢很多
        System.out.println(echoService.echo("hello, world"));
    }
}
