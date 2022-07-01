package com.roc.demo.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author dongp
 * @Date 2022/7/1 0001 16:49
 */
@RestController
public class Test {

    @GetMapping("/test")
    public String test(){
        return "helloworld";
    }
}
