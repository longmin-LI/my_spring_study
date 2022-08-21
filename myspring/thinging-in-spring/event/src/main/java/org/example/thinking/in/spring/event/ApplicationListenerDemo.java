package org.example.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @auhtor llm
 * @data 2022/8/14 0:14
 */
public class ApplicationListenerDemo {

    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();
        //向Spring应用上下文注册事件
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.out.println("接收到Spring事件 " + applicationEvent);
            }
        });
        //启动,这个操作时与事件有点关系的
        context.refresh();
        //增加start方法调用，会输出三个事件
        context.start();
        //关闭
        context.close();
    }
}
