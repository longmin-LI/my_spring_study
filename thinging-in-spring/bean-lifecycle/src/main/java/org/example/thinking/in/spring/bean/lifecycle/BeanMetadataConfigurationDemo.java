package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //实例化基于 Properties 资源的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

        String location = "MATE-INF/user.properties";
        //解决中文乱码问题
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        //会返回里面beanDefinition的个数
        int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        User user = beanFactory.getBean("user",User.class);

        System.out.println(user);
        System.out.println(beanDefinitions);
    }
}
