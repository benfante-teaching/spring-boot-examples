package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.beans.MyClass;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-conf.xml");

        MyClass o = ctx.getBean(MyClass.class);
        System.out.println(o);
    }
}
