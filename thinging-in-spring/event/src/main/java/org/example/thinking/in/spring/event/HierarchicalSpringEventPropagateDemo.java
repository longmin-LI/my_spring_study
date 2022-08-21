package org.example.thinking.in.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Spring事件传播示例
 * @auhtor llm
 * @data 2022/8/21 13:05
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {

        //1.创建parent Spring应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        //注册MyListener Bean
        parentContext.register(MyListener.class);
        //2.创建current Spring应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        //3.current -> parent
        currentContext.setParent(parentContext);
        //注册MyListener Bean
        currentContext.register(MyListener.class);

        //4.启动parent应用上下文
        parentContext.refresh();
        //5.启动current应用上下文
        currentContext.refresh();
        //6.管理所有Spring应用上下文
        currentContext.close();
        parentContext.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent>{

        //如何处理这种层次性的事件传播呢?
        //增加一个变量 做层次性的处理
        private static Set<ApplicationContextEvent> processed = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            //这样就可以避免
            if(processed.add(event)){
                System.out.printf("监听到 spring 应用上下文[id : %s] 的%s \n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }
}
