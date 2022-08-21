package org.example.thinking.in.spring.ioc.bean.scope;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import sun.security.krb5.internal.APOptions;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user(){
        return createUser();
    }
    public User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
            //创建BeanFactory
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
            //注册配置类
            applicationContext.register(ThreadLocalScopeDemo.class);

            applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
                //注册自定义 scope
                beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
            });
            //启动
            applicationContext.refresh();
            scopeByLookup(applicationContext);
            //关闭
            applicationContext.close();
        }

    private static void scopeByLookup(AnnotationConfigApplicationContext applicationContext) {

        for(int i = 0;i < 3;i++){
            Thread thread = new Thread(()->{
               User user = applicationContext.getBean("user", User.class);
               System.out.printf("Thread id: %s out user = %s %n",Thread.currentThread().getId(),user);
            });
            thread.start();
            //强制线程执行完成
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
