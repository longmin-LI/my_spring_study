package org.example.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        //如果把List改为Comparable的话，也会有输出，因为String是Comparable<String>的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        //ArrayList<Object>是List的泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
        //StringList也是List的一个泛型参数的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        Map<TypeVariable, Type> typeVariableTypeMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableTypeMap);
    }
    public static StringList getStringList(){
        return null;
    }
    public static String getString(){
        return null;
    }

    public static List<Object> getList(){//如果把E改成Object就有输出，只有这样字节码上面才会有记录，有记录才会有输出值，不然输出的就是null
        return null;
    }
    public static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentType) throws NoSuchMethodException{
        Method method = GenericTypeResolverDemo.class.getMethod(methodName);

        Class<?> type = GenericTypeResolver.resolveReturnType(method, containingClass);
        //常规类作为方法返回值
        System.out.println("GenericTypeResolver.resolveReturnType = " + type);
        //常规类型不具备泛型参数类型
        System.out.println(GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc));

    }

}
