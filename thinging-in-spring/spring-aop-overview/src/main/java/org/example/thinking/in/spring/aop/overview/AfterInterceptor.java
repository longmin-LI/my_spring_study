package org.example.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

public interface AfterInterceptor {

    /**
     * 后置动作
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
