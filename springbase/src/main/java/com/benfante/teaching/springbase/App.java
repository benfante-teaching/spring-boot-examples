package com.benfante.teaching.springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.GenericApplicationContext;
import com.benfante.teaching.springbase.service.GreetingService;
import com.benfante.teaching.springbase.service.NameService;
import com.benfante.teaching.springbase.service.impl.PropertyNameService;

@Configuration
@ComponentScan("com.benfante.teaching.springbase")
@PropertySource("classpath:application.properties")
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        GreetingService helloWorldServic = context.getBean(GreetingService.class);

        System.out.println(helloWorldServic.sayHello("Lucio"));
        System.out.println(helloWorldServic.sayHello(null));

        ((GenericApplicationContext) context).close();
    }

    @Bean
    @Primary
    public NameService defaultNameService() {
        return new PropertyNameService();
    }
}
