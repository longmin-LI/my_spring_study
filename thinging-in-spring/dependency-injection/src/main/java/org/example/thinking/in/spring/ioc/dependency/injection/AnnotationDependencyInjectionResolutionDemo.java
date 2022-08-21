package org.example.thinking.in.spring.ioc.dependency.injection;

import org.example.thinking.in.spring.ioc.dependency.injection.annotation.InjectedUser;
import org.example.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;
import static org.springframework.context.annotation.AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR;

/**
 * 注解驱动依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {


    @Autowired//DependencyDescriptor(基本元信息) ->
    // 必须+(required == true) + 实时注入(eager = true) + 通过类型（User.class) + 字段名称("user") + 是否首要(primary == true)
    @Lazy
    private User lazyUser;

    @Autowired
    private User user;

    @Autowired
    private Collection<User> users;

    @MyAutowired          // 集合类型依赖注入
    private Optional<User> userOptional;

    @Inject
    private User injectUser;

    @InjectedUser
    private User myUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //替换原有的注解处理,使用新注解
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
//        return autowiredAnnotationBeanPostProcessor;
//    }
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //原有的注解处理,增加新注解
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class,Inject.class,InjectedUser.class));
//        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return autowiredAnnotationBeanPostProcessor;
//    }
    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Order(Ordered.LOWEST_PRECEDENCE-3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return autowiredAnnotationBeanPostProcessor;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println(demo.injectUser);
        System.out.println(demo.user);
        System.out.println(demo.user==demo.injectUser);
        System.out.println("demo userOptional" + demo.userOptional);
        System.out.println("期待输出自定义的 myUser" + demo.myUser);
        applicationContext.close();
    }
}
