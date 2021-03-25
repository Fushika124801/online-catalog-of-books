package com.online.catalog.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.online.catalog.books")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
