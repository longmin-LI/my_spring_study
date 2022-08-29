package org.example.thinking.in.spring.aop.features.pointcut;

import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @auhtor llm
 * @data 2022/8/26 10:15
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private String methodName;

    private Class targetClass;

    public EchoServicePointcut() {
    }

    public EchoServicePointcut(String methodName, Class targetClass) {
        this.methodName = methodName;
        this.targetClass = targetClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return (Objects.equals(method.getName(), methodName) && this.targetClass.isAssignableFrom(targetClass));
    }
}
