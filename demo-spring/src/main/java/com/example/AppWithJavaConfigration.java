package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.beans.MyClass;

/**
 * Hello world!
 *
 */
public class AppWithJavaConfigration {
    public static void main(String[] args) throws Exception {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);

        MyClass o = ctx.getBean(MyClass.class);
        System.out.println(o);

        ((AutoCloseable)ctx).close();
    }
}
