package com.example.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.restdemo.service.UserService;
import com.example.restdemo.service.UserServiceImpl;

@SpringBootApplication
public class RestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);

        UserService userService = new UserServiceImpl();

        String cookies = userService.getUsers();
        System.out.println("Cookies: " + cookies);
        String resultCode = userService.newUser(cookies)
                            + userService.updateUser(cookies)
                            + userService.delUser(cookies);
        System.out.println("resultCode: " + resultCode);
        System.exit(0);
    }

}
