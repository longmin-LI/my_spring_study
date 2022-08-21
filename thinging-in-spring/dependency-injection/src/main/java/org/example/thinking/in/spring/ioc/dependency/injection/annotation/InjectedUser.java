package org.example.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

//自定义的依赖注解
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {

}
