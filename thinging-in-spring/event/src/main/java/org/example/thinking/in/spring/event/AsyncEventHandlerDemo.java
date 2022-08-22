package org.example.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auhtor llm
 * @data 2022/8/21 20:36
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new MySpringEventListener());

        context.refresh();

        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, SimpleApplicationEventMulticaster.class);
        if(applicationEventMulticaster instanceof SimpleApplicationEventMulticaster){
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;

            ExecutorService taskExecute = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("my_spring_event_listener_handler"));
            //同步 -> 异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecute);

            //添加一个应用上下文关闭的事件监听，一旦发生关闭线程池，避免资源的浪费
            simpleApplicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if(!taskExecute.isShutdown()){
                        taskExecute.shutdown();
                    }
                }
            });

            simpleApplicationEventMulticaster.setErrorHandler(e -> {
                System.err.println("当Spring事件异常时，原因：" + e.getMessage());
            });
        }

        context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        });

        context.publishEvent(new MySpringEvent("hello,world"));

        context.close();
    }
}
