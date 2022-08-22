package org.example.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @auhtor llm
 * @data 2022/8/21 14:30
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(Object source) {
        super(source);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage(){
        return getSource();
    }
}
