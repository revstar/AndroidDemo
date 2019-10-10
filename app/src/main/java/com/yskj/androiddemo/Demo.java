package com.yskj.androiddemo;

/**
 * Create on 2019/9/26 17:05
 * author revstar
 * Email 1967919189@qq.com
 */
public class Demo {

    public static void main(String[] args) {
//        Person p=new Child();
//        p.eat();

//        Person p=new Person() {
//            @Override
//            public void eat() {
//                System.out.println("eat foods");
//            }
//        };
//        p.eat();

        IPreson p = new IPreson() {
            @Override
            public void eat() {
                System.out.println("eat foods");

            }
        };
        p.eat();
    }
}
