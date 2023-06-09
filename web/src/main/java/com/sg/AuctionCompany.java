package com.sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-07-18 17:39
 */


@SpringBootApplication
public class AuctionCompany extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuctionCompany.class);
    }
}
