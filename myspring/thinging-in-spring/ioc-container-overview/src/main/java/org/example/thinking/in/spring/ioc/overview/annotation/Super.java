package org.example.thinking.in.spring.ioc.overview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 超级
 */
@Target(ElementType.TYPE)
//这个是保留策略，放在运行时，这样的话该注解会被记录在class文件上，并且可以被反射所使用
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {
}
