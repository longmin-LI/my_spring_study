package org.example.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Aspect配置类
 */
public class AspectXmlConfig {

    public void beforeAnyPublicMethod(){
        System.out.println("@Before any public method");
    }

    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method" + pjp.getSignature());
        return pjp.proceed();
    }

    public void finalizeAnyPublicMethod(){
        System.out.println("@After any public method");
    }

    public void afterReturningAnyPublicMethod(){
        System.out.println("@AfterReturning any public method");
    }

    public void afterThrowingAnyPublicMethod(){
        System.out.println("@AfterThrowing any public method");
    }

}
