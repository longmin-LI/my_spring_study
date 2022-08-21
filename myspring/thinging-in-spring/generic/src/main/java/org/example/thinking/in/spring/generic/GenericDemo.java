package org.example.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class GenericDemo {

    public static void main(String[] args) {

        Collection<String> list = new ArrayList<>();
        list.add("temp");
        list.add("hello");
        //直接增加，无法编译
//        list.add(1);
        //泛型擦写
        Collection c = list;
        //这样添加没有问题
        c.add(1);
        //为3
        System.out.println(list.size());
    }
}
