package org.example.thinking.in.spring.aop.features.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lilongmin
 * @date 2022/9/6 13:58
 * @description
 */
public class ExecutedEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ExecutedEvent(Object source) {
        super(source);
    }
}
