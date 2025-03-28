package com.benfante.teaching.springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import com.benfante.teaching.springbase.service.GreetingService;
import com.benfante.teaching.springbase.service.NameService;
import com.benfante.teaching.springbase.service.impl.DefaultNameService;
import com.benfante.teaching.springbase.service.impl.HelloWorldService;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("com.benfante.teaching.springbase")
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        GreetingService greetingService = context.getBean(GreetingService.class);

        System.out.println(greetingService.sayHello("Lucio"));
        System.out.println(greetingService.sayHello(null));

        ((GenericApplicationContext) context).close();
    }

    // @Bean
    // public NameService defaultNameService() {
    //     return new DefaultNameService("Pippo");
    // }

    // Uncomment this method to use a custom name service

    @Bean    // @Bean
    // public NameService defaultNameService() {
    //     return new DefaultNameService("Pippo");
    // }

    public NameService defaultNameService() {
         return new DefaultNameService("Pippo");
    }
}
