package org.example.thinking.in.spring.aop.features.advisor;

import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;

/**
 * @auhtor llm
 * @data 2022/8/30 15:33
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {

    @Override
    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }

    @Override
    public String echo(String msg) throws NullPointerException {
        return "IntroductionAdvisorDemo" + msg;
    }

    public static void main(String[] args) {
        IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();
        //通过这个方法设置，会直接把target类实现的所有接口的加进去，所以会导致我们后面的getInterfaces方法验证不到
//        ProxyFactory proxyFactory = new ProxyFactory(target);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        //添加IntroductionAdvisor
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("Before advice : " + method);
            }
        }, new IntroductionInfo() {
            @Override
            //我们在这里设置了哪个接口，哪个接口就会被传到Proxy类中的newProxyInstance方法的参数里面，如果不在里面就无法被代理
            public Class<?>[] getInterfaces() {
                return new Class[]{EchoService.class, Comparable.class};
            }
        }));

        Object proxy = proxyFactory.getProxy();

        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello, world");

        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);


    }
}
