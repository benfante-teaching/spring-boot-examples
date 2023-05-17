package com.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.spring.model.MyClass;
import com.example.spring.model.MySchool;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("demo.xml");
        MyClass myClass = ctx.getBean(MyClass.class);
        System.out.println(myClass);
        MySchool mySchool = ctx.getBean(MySchool.class);
        System.out.println(mySchool);
    }
}
