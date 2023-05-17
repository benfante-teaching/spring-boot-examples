package com.example.spring;

import java.io.Closeable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.spring.model.MyClass;
import com.example.spring.model.MySchool;

/**
 * Hello world!
 *
 */
public class AppJavaConfiguration 
{
    public static void main( String[] args ) throws Exception
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
        MyClass myClass = ctx.getBean(MyClass.class);
        System.out.println(myClass);
        MySchool mySchool = ctx.getBean(MySchool.class);
        System.out.println(mySchool);
        ((AutoCloseable)ctx).close();
    }
}
