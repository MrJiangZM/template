package com.ming.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Zaiming
 * @date 2020/3/31 10:23 上午
 */
@RestController
public class TestController {

    @GetMapping("/testAdmin")
//    @PreAuthorize("hasAnyRole('admin')")
    public String testAdmin(){
        return "testAdmin";
    }

    @GetMapping("/testUser")
//    @PreAuthorize("hasAnyRole('user')")
    public String testUser(){
        return "testUser";
    }

    @GetMapping("/testAll")
//    @PreAuthorize("hasAnyRole('user', 'admin')")
    public String testAll(){
        return "testAll";
    }

//    @NoJsonResponse
    @GetMapping("/testNone")
    public String testNone(){
        return "testNone";
    }



}
