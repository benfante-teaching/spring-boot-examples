package com.benfante.teaching.springbase;

import com.benfante.teaching.springbase.service.GreetingService;
import com.benfante.teaching.springbase.service.impl.DefaultNameService;
import com.benfante.teaching.springbase.service.impl.HelloWorldService;

public class HelloWorld {
    public static void main(String[] args) {
        GreetingService helloWorldService = new HelloWorldService(new DefaultNameService("World"));
        System.out.println(helloWorldService.sayHello("Lucio"));
        System.out.println(helloWorldService.sayHello(null));
    }
}