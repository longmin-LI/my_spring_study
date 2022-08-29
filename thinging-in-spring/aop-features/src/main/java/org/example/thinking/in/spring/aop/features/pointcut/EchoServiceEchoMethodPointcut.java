package org.example.thinking.in.spring.aop.features.pointcut;

import org.example.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @auhtor llm
 * @data 2022/8/28 10:02
 */
public class EchoServiceEchoMethodPointcut implements Pointcut {

    public static final EchoServiceEchoMethodPointcut INSTANCE = new EchoServiceEchoMethodPointcut();

    private EchoServiceEchoMethodPointcut(){

    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                //凡是EchoService接口或者EchoService子类均可
                return EchoService.class.isAssignableFrom(clazz);
            }
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "echo".equals(method.getName())
                        && method.getParameterTypes().length == 1
                        && Objects.equals("String", method.getParameterTypes()[0]);
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        };
    }
}
