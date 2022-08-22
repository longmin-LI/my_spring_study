package org.example.thinking.in.spring.annotation;

import java.lang.annotation.*;

/**
 * @auhtor llm
 * @data 2022/8/22 23:04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {


}
