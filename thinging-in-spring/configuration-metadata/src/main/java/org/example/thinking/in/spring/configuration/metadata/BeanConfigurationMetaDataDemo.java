package org.example.thinking.in.spring.configuration.metadata;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMetaDataDemo {

    public static void main(String[] args) {

        //1.我们可以通过自己new一个beanDefinition,但是这里面没有add属性的方法，所以我们换一种思路，通过BeanDefinitionBuilder来构建
//        BeanDefinition beanDefinition = new GenericBeanDefinition();
        //beanDefinition的定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","李龙敏");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //获取AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //添加附加属性，不会影响（populate,initialize）
        beanDefinition.setAttribute("name","龙哥");
        //当前bean的来源
        beanDefinition.setSource(BeanConfigurationMetaDataDemo.class);
        beanFactory.registerBeanDefinition("user",beanDefinition);
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
                    BeanDefinition bd = beanFactory.getBeanDefinition("user");
                    if(BeanConfigurationMetaDataDemo.class.equals(bd.getSource())){
                        //属性（存储）上下文
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }

                }
                return bean;
            }
        });
        //直接输出这个bean的话，他的name属性就是add的，如何利用Attribute呢？可以通过添加bbp
        System.out.println(beanFactory.getBean("user",User.class));


    }
}
