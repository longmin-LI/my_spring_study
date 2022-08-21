package org.example.thinking.in.spring.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

//@Configuration是非必须类
public class ObjectProviderDemo {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> iterator = beanProvider;
//        for(String string : iterator){
//            System.out.println(string);
//        }
        beanProvider.stream().forEach(System.out :: println);
    }

    @Bean
    @Primary
    public String helloWorld(){
    //方法名就是默认的Bean名称，并且由于这个Bean定义在ObjectProviderDemo类里面，他默认就是一个配置类，因为你在register里注册了他，所以加不加@Configuration无所谓
        return "Hello,World";
    }
    @Bean
    public String message(){
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println("当前对象 " + user);
    }
}
