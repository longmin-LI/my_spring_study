package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.ioc.overview.annotation.Super;
import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
    }

    public static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String[] locations = {"META-INF/dependency-lookup-context.xml","MATE-INF/bean-constructor-dependency-injection.xml"};

        int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(locations);

        System.out.println("bean的数目：" + beanDefinitions);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser",SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(userHolder);
    }
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("superUser",beanName) && SuperUser.class.equals(beanClass)){
                //把配置完成的superUser覆盖
                return new SuperUser();
            }
            return null;//保持Spring IoC容器的实例化操作
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            //返回false的时候，代表之不允许属性赋值，就是起到一个拦截的作用
            if(ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
                //手动的方式去配置所有的属性
                User user = (User) bean;
                user.setId(1L);
                user.setName("you");
                return false;
            }
            return true;
        }
        //注意如果bean之前被postProcessAfterInstantiation拦截，那么这个方法会被跳过,即user bean跳过属性赋值
        //对于super来说，他是完全跳过了bean的实例化的阶段，所以也会跳过bean属性赋值阶段
        //userHolder
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
                throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                //<!-- <property name="number" value="1"/>-->如果在XML进行这样的配置的话，那么在propertyValues就包含一个 PropertyValue(number = 1)
                MutablePropertyValues propertyValues;
                if(pvs instanceof MutablePropertyValues){
                    propertyValues = (MutablePropertyValues) pvs;
                }else{
                    propertyValues = new MutablePropertyValues();
                }
                //下面这是给属性赋值的操作，也可以修改
                //XML配置等效于下面的代码
                propertyValues.addPropertyValue("number",1);
                //下面的操作为修改，首先拦截
                if(propertyValues.contains("desc")){
                    //可以get获得propertyValue,但是进去类里面，发现他的value属性是final的，即不可被修改
                    //PropertyValue propertyValue = propertyValues.getPropertyValue("desc");
                    propertyValues.removePropertyValue("desc");
                    propertyValues.addPropertyValue("desc","the user holder V2");
                }
                return propertyValues;
            }
            return null;
        }
    }
}
