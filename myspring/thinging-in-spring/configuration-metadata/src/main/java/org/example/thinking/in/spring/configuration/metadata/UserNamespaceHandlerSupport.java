package org.example.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @link(NamespaceHandlerSupport)
 */
public class UserNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //将"user"元素注册对应的beanDefinitionParse实现
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
