package com.chrislaforetsoftware.mockingcontextspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.chrislaforetsoftware")
@EntityScan("com.chrislaforetsoftware")
public class MockingContextSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockingContextSpringApplication.class, args);
    }
}
