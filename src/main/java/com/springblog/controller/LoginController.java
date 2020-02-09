package com.springblog.controller;

import com.springblog.service.UserService;
import com.springblog.pojo.User;
import com.springblog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    //允许跨域
    @CrossOrigin
    @PostMapping(value = "api/login")
    public Result login(@RequestBody User requser){
        User user = userService.get(requser);
        if(user==null){
            return new Result(400);
        }
        return new Result(200);
    }

    @GetMapping(value = "/index")
    public String index(){
        return "hello";
    }

}
