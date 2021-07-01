package com.lopez.rafael.restfulwebservice.controllers;

import com.lopez.rafael.restfulwebservice.models.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeBeanController {
    @GetMapping("someBean")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("Field A", "Field B", "Field C");
    }
}
