package org.example.topic3.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = {"org.example.topic3.demo2"})
// Importing the xml where the alias is defined.
@ImportResource("topic3/demo2/topic3-application-context-demo2.xml")
public class AppConfig {

    //methods number, digits, and digits2 are suitable candidates for injection, find out which will get injected.
    @Bean
    Integer number()
    {
        return 2;
    }

    @Bean
    Integer digits()
    {
        return 1;
    }

    @Bean(name="digits")
    Integer digits2()
    {
        return 8;
    }

    @Bean
    Integer repeat()
    {
        return 3;
    }

    @Bean
    String someVal()
    {
        return "!!!";
    }

    //someVal is automatically injected here
    @Bean
    Runner runner(String someVal)
    {
        return new Runner(someVal);
    }
}
