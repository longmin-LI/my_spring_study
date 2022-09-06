package org.example.thinking.in.spring.aop.features.event;

/**
 * @author lilongmin
 * @date 2022/9/6 13:57
 * @description
 */
public class Executor {//classFilter来过滤

    public void execute(){//就是一个join point，需要被pointcut进行匹配，methodmatcher
        System.out.println("执行中");
    }

    public void test(){
        System.out.println("非aop拦截方法执行。。。");
    }
}
