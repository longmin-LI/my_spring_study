package org.example.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency作为依赖来源
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class,"hello,world");

        });

        applicationContext.refresh();
        //这样处理会出现问题，1.如果我们把容器的启动放在下面这段代码的上面，那么就会因为ResolvableDependencySourceDemo这个类的依赖没办法解析爆出NoSuchBeanDefinition异常
        //2.如果这段代码放在下面，则会爆出容器还没有启动这个错误，无法注册，处理方式把下面这段代码替换成一个BeanFactoryPostProcessor
//        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//
//        if(beanFactory instanceof ConfigurableListableBeanFactory){
//            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
//            //注册Resolvable Dependency
//            configurableListableBeanFactory.registerResolvableDependency(String.class,"hello,world");
//        }


        applicationContext.close();
    }
}
