package org.example.thinking.in.spring.aop.features;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @auhtor llm
 * @data 2022/8/29 14:45
 */
public class MyThrowsAdvice implements ThrowsAdvice {


    public void afterThrowing(Exception e){
        System.out.printf("Exception : %s \n", e);
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e){
        System.out.printf("Method : %s, args : %s , target : %s, exception : %s \n",
                method, Arrays.asList(args), target, e);
    }


}
