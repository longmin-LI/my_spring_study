package org.example.thinking.in.spring.validation;

import org.example.thinking.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {

        //可以加上stopClass来排除一些你不想要看到的类的相关信息，注意必须是和你的主类有继承或者实现关系的
        //getBeanInfo方法里面必须传递一个具体的类，不能是一个接口或者抽象类，否则会抛出异常
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

        //属性描述符PropertyDescriptors
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            //Getter方法
            propertyDescriptor.getReadMethod();
            //Setter方法
            propertyDescriptor.getWriteMethod();
            System.out.println(propertyDescriptor);
        });
        //注意这里只需要你的方法满足get set开头就行，而且set 和 get不需要成对出现
        //会输出所有方法，包括Object类中的方法
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
