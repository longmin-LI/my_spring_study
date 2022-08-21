package org.example.thinking.in.spring.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    //1.实现setAsText这个方法
    @Override
    public void setAsText(String test) throws IllegalArgumentException{
        //2.将String类型转换成Properties类型
        Properties properties = new Properties();

        try {
            properties.load(new StringReader(test));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        //3.临时存储 Properties 对象
        setValue(properties);

        // next 获取临时 Properties 对象，通过getValue方法

    }
    //为什么要实现这个方法，而不是直接通过value呢？
    //答：因为要尽可能的不改变他的实现，他原因是String，我们获取的时候，也要获取String
    @Override
    public String getAsText(){
        Properties properties = (Properties) getValue();

        StringBuilder text = new StringBuilder();

        for(Map.Entry<Object, Object> entry : properties.entrySet()){
            text.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }
        return text.toString();
    }

}
