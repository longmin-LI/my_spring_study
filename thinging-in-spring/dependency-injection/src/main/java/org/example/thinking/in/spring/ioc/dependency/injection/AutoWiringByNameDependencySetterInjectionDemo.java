package org.example.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Autowiring ByType
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

    public static void main(String[] args) {
        //这里建了一个空的DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //下面把资源路径和register传进去
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/MATE-INF/autowiring-dependency-setter-injection.xml";
        //加载XML资源，解析并且生成BeanDefinitions
        reader.loadBeanDefinitions(location);
        //依赖查找，并且创建Bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
