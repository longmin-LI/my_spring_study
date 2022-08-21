package org.example.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @auhtor llm
 * @data 2022/8/14 0:14
 */
//激活异步
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        //基于接口的spring事件监听
//        GenericApplicationContext context = new GenericApplicationContext();
        //基于注解实现
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册引导类
        context.register(ApplicationListenerDemo.class);
        //方法一：用接口，向Spring应用上下文注册事件
        //a.基于ConfigurableApplicationContext实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                print("ApplicationEvent" + "接收到Spring事件 " + applicationEvent);
            }
        });

        //基于Spring注解 @org.springframework.context.event.EventListener
        //b方法：基于ApplicationListener注册为SpringBean

        //启动,这个操作时与事件有点关系的
        context.refresh();
        //增加start方法调用，会输出三个事件
        context.start();
        //关闭
        context.close();
    }
    //注意我们这里是把ApplicationEvent作为事件，所以能够监听到和ApplicationContext相关的诸多事件，我们也可以改为对特定事件的监听
//    @EventListener
//    public void onApplicationEvent(ApplicationEvent event){
//        System.out.println("@EventListener" + "接收到Spring事件 " + event);
//    }

    @EventListener
    @Order(2)
    public void onApplicationEvent(ContextClosedEvent event){
        print("@EventListener (ContextClosedEvent)" + "接收到Spring close事件 " + event);
    }

    @EventListener
    @Order(1) //还可以通过order来定义方法执行的顺序，里面的数字越小说明执行的优先级越高，
    //更深层次一点的原因在于，因为对于java反射来说，getMethods方法返回的方法并不是有序的
    public void onApplicationEvent1(ContextClosedEvent event){
        print("@EventListener (ContextClosedEvent1)" + "接收到Spring close事件 " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        print("@EventListener" + "接收到Spring refresh事件 " + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event){
        print("@EventListener" + "接收到Spring start事件 " + event);
    }
    //验证异步
    @EventListener
    @Async
    public void onApplicationEventAsyc(ContextStartedEvent event){
        print("@EventListener" + "接收到Spring start事件 " + event);
    }

    private static void print(Object printable){
        System.out.printf("线程 %s] : %s \n", Thread.currentThread().getName(), printable);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello, world") {
        });

        applicationEventPublisher.publishEvent("hello, world");
    }
}
