package com.going.aas.conf;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.going.aas")
@MapperScan("com.going.aas.persistence")
public class AasApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AasApplication.class, args);
    }

}