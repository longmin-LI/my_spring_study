package org.example.thinking.in.spring.bean.lifecycle;

import org.example.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

public class BeanLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加BeanPostProcessor,实现InstantiationAwareBeanPostProcessor，主要用来处理关于实例化前后的生命周期管理
        //他也可以用来实现初始化前后的管理，因为他实现了bbp接口
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //激活@PostConstruct注解，关于初始化前的生命周期回调
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        //增加销毁前的生命周期回调,注意这些bbp的处理都是按照顺序的，如果你把这个放在common的下面，那么就会出现V9在V10下面的情况
        //因为@PreDestroy的处理是在common的bbp中进行的
//        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitions = reader.loadBeanDefinitions(new String[]{"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"});

        System.out.println("bean的数量：" + beanDefinitions);

        beanFactory.preInstantiateSingletons();

        User user = beanFactory.getBean("user",User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder: " + userHolder);
        //销毁只是在容器内销毁，并不是被GC掉了
        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);

    }
}
