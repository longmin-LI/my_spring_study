package org.example.thinking.in.spring.conversion;

import java.beans.PropertyEditorSupport;

/**
 * @see java.beans.PropertyEditor
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        //模拟Spring Framework的操作
        String text = "name = 李龙敏";

        PropertyEditorSupport propertyEditorSupport = new StringToPropertiesPropertyEditor();

        propertyEditorSupport.setAsText(text);

        System.out.println(propertyEditorSupport.getValue());
        System.out.println(propertyEditorSupport.getAsText());
    }
}
