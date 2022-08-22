package org.example.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auhtor llm
 * @data 2022/8/21 21:00
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedAsyncEventHandlerDemo.class);

        context.refresh();
        //发布自定义事件
        context.publishEvent(new MySpringEvent("caonimabi"));
        context.close();
    }

    @EventListener
    @Async
    public void onEvent(MySpringEvent event){
        System.out.printf("[线程：%s] onEvent方法监听到事件 ： %s \n", Thread.currentThread().getName(), event);
    }
    @Bean
    public Executor taskExecutor(){
        return Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my-event-thread-poll"));
    }
}
