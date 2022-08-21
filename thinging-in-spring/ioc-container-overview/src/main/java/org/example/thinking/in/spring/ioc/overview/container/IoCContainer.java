package org.example.thinking.in.spring.ioc.overview.container;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IoC容器实例
 */
public class IoCContainer {

    public static void main(String[] args) {
        //1.创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML配置文件的classpath路径
        String location = "META-INF/dependency-lookup-context.xml";
        int beanDefinitions = reader.loadBeanDefinitions(location);
        //输出你在XML文件中配置的Bean的数量
        System.out.println(beanDefinitions);
        //依赖查找集合对象
        lookupCollectionByType(beanFactory);
    }
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory factory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = factory.getBeansOfType(User.class);
            System.out.println("查找到的所有集合对象" + beans);
        }
    }

}
