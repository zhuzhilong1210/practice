package com.me.user.controller;

import com.base.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Administrator
 * Date: 2019-01-06 15:58:00
 * Comment:
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        System.out.println("hello");
        return super.response(id);
    }

    /*@PostMapping
    public User addUser(@RequestBody User user){

    }*/
}
