package org.example.thinking.in.spring.conversion;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        //下面这步的具体的方法在finishBeanFactoryInitialization
        //AbstractApplicationContext -> "conversionService" ConversionService Bean
        //-> ConfigurableBeanFactory#serConversionService() -> AbstractAutowireCapableBeanFactory#instantiateBean
        //-> AbstractBeanFactory#getConversionService
        //->beanDefinition -> BeanWrapper -> 属性转换 PropertyValues
        //setPropertyValues -> TypeConverter#convertIfNecessnary
        //TypeConverterDelegate#convertIfNecessnary -> PropertyEditor or ConversionService
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/property-editors-context.xml");


        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
