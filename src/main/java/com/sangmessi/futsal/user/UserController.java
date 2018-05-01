package com.sangmessi.futsal.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User currentUser(){
        User user = new User();
        return user;
    }


}
