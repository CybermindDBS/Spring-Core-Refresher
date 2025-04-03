package org.example.topic3.demo1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("topic3/demo1/topic3-application-context-demo1.xml");
        String str = (String) context.getBean("bean1");
        System.out.println(str);
    }
}
