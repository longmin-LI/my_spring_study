package org.example.thinking.in.spring.conversion;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

 //声明为一个bean
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //1.通用类型转换
        //2.Java Beans 属性类型转换

        registry.registerCustomEditor(User.class, "properties", new StringToPropertiesPropertyEditor());


    }
}
