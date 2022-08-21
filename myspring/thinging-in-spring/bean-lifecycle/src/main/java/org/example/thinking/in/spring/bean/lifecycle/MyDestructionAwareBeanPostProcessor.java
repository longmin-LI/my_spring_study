package org.example.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDesc("the user holder v9");
            System.out.println("postProcessBeforeDestruction: " + userHolder.getDesc());
        }
    }
}
