package org.example.thinking.in.spring.aop.features.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author lilongmin
 * @date 2022/9/6 13:59
 * @description
 */
public class StaticExecutor implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void execute(){
        System.out.println("执行中");
        eventPublisher.publishEvent(new ExecutedEvent(this));
    }
}
