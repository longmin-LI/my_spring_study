package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建BeanDefinition
 */
public class BeanDefinitionCreate {

    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置,这种配置方式和XML配置方式差不多，只是XML里面的value全是字符类型，但是这里可以用更多的类型，避免解析
        beanDefinitionBuilder.addPropertyValue("id",1);
        beanDefinitionBuilder.addPropertyValue("name","llm");
        //可以通过链式调用来简化
//        beanDefinitionBuilder
//                .addPropertyValue("id",1)
//                .addPropertyValue("name","llm");
        //获取BeanDefinition实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //可以后续的补充BeanDefinition的相应属性，并非Bean的终态，可以自定义修改

        //2,通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过MutablePropertyValues实现批量属性操作
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue("id",1);
        propertyValues.addPropertyValue("name","llm");
        //这里也可以通过add来链式调用
//        propertyValues
//                .add("id",1)
//                .add("name","llm");
        //通过set MutablePropertyValues来批量操作属性
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
