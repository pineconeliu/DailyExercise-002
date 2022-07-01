package com.lss.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringDemoApplication {

    @Autowired
    private Lock lock;
    @Autowired
    private DistributedLock distributedLock;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

@Configuration
class Config{

    @Bean
    public Lock lock(){
        return new DistributedLock();
    }

}
interface  Lock{}

class DistributedLock implements  Lock{}


}
