package com.roc.demo.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author dongp
 * @Date 2022/7/1 0001 18:00
 */
@RestController
public class Test {

    @GetMapping("/test")
    public String test(){
        return "helloworld";
    }
}
