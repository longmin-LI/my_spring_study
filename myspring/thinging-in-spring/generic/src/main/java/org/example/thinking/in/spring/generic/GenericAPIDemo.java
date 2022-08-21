package org.example.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericAPIDemo {

    public static void main(String[] args) {

        //原生类型 primtive Type int long float
        Class intClass = int.class;
        //数组类型array type：int[] Object[]
        Class objectArrayClass = Object[].class;

        //原始类型 raw type ： java.lang.String
        Class rowType = String.class;

        //参数泛型类型：parameterized type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        //parameterizedType.getRawType() = java.util.AbstractList

        //泛型类型变量 ：Type Variable
        System.out.println(parameterizedType.toString());
        //可以看到这个里面的每个元素的类型都为：TypeVariableImpl
        Type[] types = parameterizedType.getActualTypeArguments();

        //输出<E>
        Stream.of(types)
                .map(TypeVariable.class :: cast)//将其强转为TypeVariable,
                .forEach(System.out::println);

    }
}
