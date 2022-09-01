package org.example.thinking.in.spring.questions;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @auhtor llm
 * @data 2022/8/24 23:36
 */
public class ObjectFactoryLazyLookDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册
        context.register(ObjectFactoryLazyLookDemo.class);

        context.refresh();

        ObjectFactoryLazyLookDemo bean = context.getBean(ObjectFactoryLazyLookDemo.class);
        //true
        System.out.println(bean.objectFactory.getClass() == bean.objectProvider.getClass());
        //false
        System.out.println(bean.objectFactory == bean.objectProvider);
        //下面两个输出的是同一个bean，并且他们在运行的时候类型为DefaultListableBeanFactory里面的一个DependencyObjectProvider内部类
        System.out.println(bean.objectFactory.getObject());
        System.out.println(context.getBean(User.class));

        context.close();
    }

    @Autowired
    private ObjectFactory<User> objectFactory;

    @Autowired
    private ObjectProvider<User> objectProvider;

    @Bean
    @Lazy
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("llm");
        return user;
    }


}
