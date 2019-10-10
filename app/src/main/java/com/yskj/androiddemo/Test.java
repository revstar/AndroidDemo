package com.yskj.androiddemo;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Create on 2019/9/30 15:36
 * author revstar
 * Email 1967919189@qq.com
 */
public class Test {


    public static void main(String[] args) {
            int amount;
        Collection set=new HashSet();

        List<String> list=new ArrayList<>();
        String data[]={"ert","rtrtw","eter","ertw"};
        list=Arrays.asList(data);

        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println("输出元素>"+iterator.next());
        }
    }


}
