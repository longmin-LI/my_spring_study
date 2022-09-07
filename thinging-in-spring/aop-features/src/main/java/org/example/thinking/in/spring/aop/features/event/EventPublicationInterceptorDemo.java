package org.example.thinking.in.spring.aop.features.event;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * @author lilongmin
 * @date 2022/9/6 13:57
 * @description
 */
@Configuration
@EnableAspectJAutoProxy
public class EventPublicationInterceptorDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(EventPublicationInterceptorDemo.class, Executor.class, StaticExecutor.class);

        context.refresh();

        //5.执行
        Executor executor = context.getBean(Executor.class);
        executor.execute();
        executor.test();

        StaticExecutor staticExecutor = context.getBean(StaticExecutor.class);
        staticExecutor.execute();


        context.close();
    }

    //1.将EventPublicationInterceptor作为一个spring的bean
    @Bean
    public EventPublicationInterceptor eventPublicationInterceptor(){
        EventPublicationInterceptor eventPublicationInterceptor = new EventPublicationInterceptor();
        //关联目标自定义事件类型 -> ExecutedEvent
        eventPublicationInterceptor.setApplicationEventClass(ExecutedEvent.class);
        return eventPublicationInterceptor;
    }

    //2.实现pointcut
    @Bean
    public StaticMethodMatcherPointcut pointcut(){
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "execute".equals(method.getName()) && Executor.class.equals(targetClass);
            }
        };
    }

    //3.声明一个advisor bean
    @Bean
    public PointcutAdvisor pointcutAdvisor(StaticMethodMatcherPointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor){
        return new DefaultPointcutAdvisor(pointcut, eventPublicationInterceptor);
    }

    //4.处理事件
    @EventListener(ExecutedEvent.class)
    public void executed(ExecutedEvent event){
        System.out.println("executed event : " + event);
    }
}

