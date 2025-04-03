package org.example.topic3.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.security.SecureRandom;

public class Demo2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(org.example.topic3.demo2.AppConfig.class);
        System.out.println("----------<first run>----------");
        context.getBean(org.example.topic3.demo2.WishMessageGenerator.class).run();
        System.out.println("----------<second run>----------");
        context.getBean(org.example.topic3.demo2.WishMessageGenerator.class).run();
    }
}

@Component
@Scope(scopeName = "singleton")
@PropertySource("topic3/demo2/config.properties")
class WishMessageGenerator {
    @Value("${sentence}") //${} for injecting values from application.properties, And #{} for evaluating expression.
    String msg;

    @Autowired
    // Example for alias and usage of it in @Qualifier (to load a bean based on a value from .properties file), or we if we don't want to use alias then type the bean name directly in qualifier.
    @Qualifier("myAlias")
    Integer loop;

    @Autowired
    RandomNumberGenerator randomNumberGenerator;

    @Autowired
    Runner runner;

    Integer rngVal;

    @PostConstruct
    void afterInitialization()
    {
        System.out.println("WMG Object initiated!");
    }

    @PreDestroy
    void beforeDestroy()
    {
        System.out.println("WMG Object destroyed during garbage collection!");
    }

    void run()
    {
        runner.doIt(randomNumberGenerator, this);
    }
}

@Component
class RandomNumberGenerator {
    Integer digits;

    @Autowired
    public RandomNumberGenerator(@Qualifier("digits") Integer digits) {
        this.digits = digits;
    }

    Integer give()
    {
        //Logic for maxBound
        int maxBound = 1;
        for(int i = digits; i > 0; i--)
        {
            maxBound *= 10;
        }
        maxBound -= 1;

        return new SecureRandom().nextInt(0,maxBound);
    }
}

class Runner {
    String someVal;

    public Runner(String someVal) {
        this.someVal = someVal;
    }

    void doIt(RandomNumberGenerator rng, WishMessageGenerator wmg)
    {
        if(wmg.rngVal == null || wmg.rngVal == 0) wmg.rngVal = rng.give();
        String msg = wmg.msg;
        for(int i = wmg.loop; i > 0; i--)
        {
            System.out.println(msg + " " + wmg.rngVal + someVal);
        }
    }
}
