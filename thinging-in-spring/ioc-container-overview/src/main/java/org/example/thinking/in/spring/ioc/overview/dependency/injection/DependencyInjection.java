package org.example.thinking.in.spring.ioc.overview.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.example.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入实例
 */
public class DependencyInjection {

    public static void main(String[] args) {
//        BeanFactory applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String s : beanDefinitionNames){
            System.out.println(s);
        }
        //依赖来源1：自定义Bean
        UserRepository repository = applicationContext.getBean("userRepository", UserRepository.class);
        //XML文件里有配置根据名称和类型注入
//        System.out.println(repository.getUsers());
        System.out.println(repository);
        //这里获取对象的方式是依赖注入，他就可以获取到这个东西
        //依赖来源2：依赖注入（内建依赖）
        System.out.println(repository.getBeanFactory());
        //org.springframework.beans.factory.support.DefaultListableBeanFactory@60addb54: defining beans
        //[user,objectFactoryCreatingFactoryBean,superUser,userRepository]; root of factory hierarchy

        //返回的是false
//        System.out.println(applicationContext == repository.getBeanFactory());

        //这样子是属于依赖查找，因为我们没有定义BeanFactory这个Bean，所以会报错NoSuchBeanDefinitionException
//        System.out.println(applicationContext.getBean(BeanFactory.class));
        //这里输出的就是你前面配置的User类型的Bean，但是注意，因为他不是集合类型，所以只会输出一个，就是标记为Primary的那个bean
//        ObjectFactory<User> objectFactory = repository.getObjectFactory();

        ObjectFactory<ApplicationContext> objectFactory = repository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        //true
        System.out.println(objectFactory.getObject() == applicationContext);

        //依赖来源三：容器内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的bean" + environment);
        whoIsIoCContainer(repository,applicationContext);

    }
    public static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext){
        //这个式子为什么不成立
        System.out.println(applicationContext == userRepository.getBeanFactory());
        //ApplicationContext is BeanFactory

    }
}