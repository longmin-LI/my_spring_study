package org.example.thinking.in.spring.data.binding;

import org.example.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {

    public static void main(String[] args) {
        //1.创建空白对象
        User user = new User();
        DataBinder binder = new DataBinder(user);
        //2.创建propertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "小马哥");
        //PropertyValues中存在User中不存在的属性值
        //DataBinder忽略未知对象
        source.put("age", 18);
        //PropertyValues中存在一个嵌套属性，比如company.name
        //DataBinder支持嵌套属性
        //正常的步骤：new Company();
        //company.setName("geekbang")
        //user.setCompany(company) 这些步骤框架帮我们做了
        source.put("company.name", "geekbang");
        PropertyValues propertyValues = new MutablePropertyValues(source);

        //如果我们对binder做一些参数的调整，结果就会不一样
        //1.setIgnoreUnknownFields 由true -> false 会抛出异常，因为user中不包含age属性
//        binder.setIgnoreUnknownFields(false);
        //2.setAutoGrowNestedPaths 由true -> false 会抛出异常，因为company是嵌套的属性，如果不允许自动嵌套，就会出错
        binder.setAutoGrowNestedPaths(false);
        //3.setIgnoreInvalidFields 有false -> true，这样的话就会忽略掉无效的绑定，不会出现异常，但是company的name的值为null
        //并且需要搭配setAutoGrowNestedPaths为false一起使用
        binder.setIgnoreInvalidFields(true);
        //4.设置白名单
        binder.setRequiredFields("id", "name", "city");
        binder.bind(propertyValues);

        System.out.println(user);

        //4.获取绑定结果 (包含错误文案)
        BindingResult result = binder.getBindingResult();
        //打印出错误信息org.springframework.validation.BeanPropertyBindingResult: 1 errors
        //Field error in object 'target' on field 'city': rejected value []; codes [required.target.city,required.city,required.org.example.thinking.in.spring.ioc.overview.enums.City,required]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable:
        // codes [target.city,city]; arguments []; default message [city]]; default message [Field 'city' is required]
        System.out.println(result);
    }
}
