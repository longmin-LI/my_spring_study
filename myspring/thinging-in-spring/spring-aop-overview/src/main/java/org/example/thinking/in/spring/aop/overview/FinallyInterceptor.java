package org.example.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

public interface FinallyInterceptor {

    Object finalize(Object proxy, Method method, Object[] args, Object returnResult);
}
