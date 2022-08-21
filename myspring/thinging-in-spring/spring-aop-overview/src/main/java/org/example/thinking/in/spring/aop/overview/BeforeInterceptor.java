package org.example.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

//抽象出来的before动作
public interface BeforeInterceptor {
    /**
     * 前置执行
     */
    Object before(Object proxy, Method method, Object[] args);
}
