package org.example.thinking.in.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TargetFilterDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        String targetClass = "org.example.thinking.in.spring.aop.overview.EchoService";
        //获取当前classloader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //获取目标类
        Class<?> target = classLoader.loadClass(targetClass);
        //我们要找到的是它里面的echo方法
        //Spring的反射工具类ReflectionUtils
        //下面是通过名称和参数进行过滤
        Method method = ReflectionUtils.findMethod(target, "echo",String.class);
        System.out.println(method);
        //通过抛出的异常类型来判断
        //查找方法，throws异常类型为NullPointException
        //callback就是后续的一个动作
        ReflectionUtils.doWithMethods(target, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅仅抛出NullPointException ----" + method.getName());
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                Class<?>[] parameterTypes = method.getParameterTypes();
                return exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0])
                        && parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0]);
            }
        });
    }
}
