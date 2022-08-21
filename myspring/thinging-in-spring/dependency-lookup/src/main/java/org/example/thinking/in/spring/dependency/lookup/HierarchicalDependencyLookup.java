package org.example.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 层次性依赖查找
 */
public class HierarchicalDependencyLookup {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ObjectProviderDemo.class);
        //1.获取HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        //System.out.println("当前BeanFactory的parent BeanFactory" + beanFactory.getParentBeanFactory());
        //displayLocalBean(beanFactory,"user");
        //displayLocalBean(parentBeanFactory,"user");
        displayContainsBean(beanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");
        applicationContext.refresh();
        applicationContext.close();
    }
    private static HierarchicalBeanFactory createParentBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
    //演示什么是localBean
    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName){
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n",beanFactory,beanName,beanFactory.containsLocalBean(beanName));
    }
    //对于双亲委派的一个自己的实现
    private static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("当前 BeanFactory[%s] 是否包含 bean[name : %s] : %s\n",beanFactory,beanName,containsBean(beanFactory,beanName));
    }
    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(hierarchicalBeanFactory,beanName)){
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }
}
