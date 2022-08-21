package org.example.thinking.in.spring.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.jws.soap.SOAPBinding;

public class TypeSafeDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafeDependencyLookupDemo.class);
        applicationContext.refresh();
        //演示BeanFactory#getBean方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        //演示ObjectFactory#getObject方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        //上面两个都是不安全的，演示ObjectProvider#ifAvailable,这是安全的
        displayObjectProviderIfAvailable(applicationContext);
        //集合类型的都是安全的
        displayListableBeanFactory(applicationContext);
        applicationContext.close();
    }

    private static void displayListableBeanFactory(ListableBeanFactory applicationContext) {
        printBeanException("displayListableBeanFactory: ", ()->applicationContext.getBeansOfType(User.class));

    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderIfAvailable: ",()->beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //ObjectProvider is ObjectFactory
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetObject: ",() -> beanProvider.getObject());
    }

    //注意这个getBean方法，会返回三种异常：
    /*
    NoSuchBeanDefinitionException – if there is no such bean definition
    BeanDefinitionStoreException – if arguments have been given but the affected bean isn't a prototype
    BeansException – if the bean could not be created
     */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory){
//        try {
//            beanFactory.getBean(User.class);
//        }catch (BeansException exception){
//            exception.printStackTrace();
//        }
        //可以用下面的lambda表达式来代替上面那些代码
        printBeanException("displayBeanFactoryGetBean: ",() -> beanFactory.getBean(User.class));
    }
    private static void printBeanException(String msg, Runnable runnable){
        //这样打印的信息会出现在异常里面
        System.err.println(msg + "from");
        try {
            runnable.run();
        }catch (BeansException exception){
            exception.printStackTrace();
        }
    }
}
