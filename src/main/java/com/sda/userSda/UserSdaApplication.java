package com.sda.userSda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class UserSdaApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserSdaApplication.class, args);
    }

}
