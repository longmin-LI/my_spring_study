package org.example.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Aspect配置类
 */
@Aspect
public class AspectConfiguration {

    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod(){
        System.out.println("@Point cut any public method");
    }

    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod(){
        System.out.println("@Before any public method");
    }
    //Around注解拦截以后，需要自己手动的去调用方法，不然的话无法执行
    @Around("anyPublicMethod()")
    public Object aroundAnyPublicMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around any public method");
        return pjp.proceed();
    }

    @After("anyPublicMethod()")
    public void finalizeAnyPublicMethod(){
        System.out.println("@After any public method");
    }

    @AfterReturning("anyPublicMethod()")
    public void afterReturningAnyPublicMethod(){
        System.out.println("@AfterReturning any public method");
    }

    @AfterThrowing("anyPublicMethod()")
    public void afterThrowingAnyPublicMethod(){
        System.out.println("@AfterThrowing any public method");
    }

}
