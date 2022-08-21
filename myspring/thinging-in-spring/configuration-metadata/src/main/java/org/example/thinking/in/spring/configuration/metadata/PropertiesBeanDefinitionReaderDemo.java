package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        //创建IoC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建面向properties的 beanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //如何解决乱码问题
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("META-INF/users-config.properties");
        //转换为带有字符集编码的EncodeResource
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");

        int definitionCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(definitionCount);
        //通过依赖查找的方式来获取bean，没有指定BeanName的时候，他的name位user
        //并且一旦在properties文件里面定义了某个属性，那么你在其他的文件里面定义就失效了，因为加载这个properties文件是有序的
        User user = beanFactory.getBean(User.class);
        System.out.println(user.getId().getClass());
        System.out.println(user);
    }
}
