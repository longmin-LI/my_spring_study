package beans;

import java.beans.*;
import java.util.stream.Stream;


public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            //PropertyDescriptor允许添加属性编辑器 - PropertyEditor
            //GUI -> text(String) -> PropertyType
            //name -> String
            //age -> Integer
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if("age".equals(propertyName)){
                //为"age"字段增加PropertyEditor
                //要把String转换为Integer
                propertyDescriptor.createPropertyEditor(StringToIntegerPropertyEditor.class);
                propertyDescriptor.createPropertyEditor(new Person());
            }
        });
    }
    //需要继承PropertyEditorSupport，这个类，这个类实现了PropertyEditor接口，做了一些比较好的封装
    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        //这个方法就是注入属性的值
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(text);
        }
    }
}
