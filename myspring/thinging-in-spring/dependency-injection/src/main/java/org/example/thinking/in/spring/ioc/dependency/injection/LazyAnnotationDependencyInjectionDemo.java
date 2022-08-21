package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * {@link org.springframework.beans.factory.ObjectProvider}
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired//实时注入
    private User user;

    @Autowired//延迟注入
    private ObjectProvider<User> objectProvider;

    @Autowired//ObjectFactory来输出集合类型
    private ObjectFactory<Set<User>> objectFactory;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        reader.loadBeanDefinitions(location);
        applicationContext.refresh();
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println(demo.objectProvider.getObject());
        System.out.println(demo.user);
        //objectProvider他可以输出集合类型的Bean
        demo.objectProvider.forEach(System.out :: println);
        System.out.println(demo.objectFactory.getObject());
        applicationContext.close();
    }
}
