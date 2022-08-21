package org.example.thinking.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.xml.ws.Action;

/**
 * @auhtor llm
 * @data 2022/8/21 14:23
 */
public class InjectingApplicationEventPublisherDemo implements ApplicationContextAware, ApplicationEventPublisherAware {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        //# 3
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
        // #4
        applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectingApplicationEventPublisherDemo.class);

        //增加事件监听器
        context.addApplicationListener(new MySpringEventListener());

        context.refresh();

        context.close();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //#2取决为哪个aware接口先被处理，在ApplicationContextAwareProcessor被处理
        applicationEventPublisher.publishEvent(new MySpringEvent("hello,llm from applicationContextAware"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //#1
        applicationEventPublisher.publishEvent(new MySpringEvent("hello,llm from applicationEventPublisherAware"));
    }
}
