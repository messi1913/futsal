package com.sangmessi.futsal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class SpringBootJDBCSessionController {

    @GetMapping("/viewSessionData")                     // it will handle all request for /welcome
    public java.util.Map<String,Object> start(HttpServletRequest request) {
        Integer integer =(Integer) request.getSession().getAttribute("hitCounter");  //it will read data from database tables
        if(integer==null){
            integer=new Integer(0);
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }else{
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }
        java.util.Map<String,Object> hitCounter=new HashMap<>();
        hitCounter.put("Hit Counter",integer);
        hitCounter.put("Session ID", request.getSession().getId());
        return hitCounter;
    }
}
