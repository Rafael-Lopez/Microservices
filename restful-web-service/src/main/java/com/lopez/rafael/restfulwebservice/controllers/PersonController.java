package com.lopez.rafael.restfulwebservice.controllers;

import com.lopez.rafael.restfulwebservice.models.Name;
import com.lopez.rafael.restfulwebservice.models.PersonV1;
import com.lopez.rafael.restfulwebservice.models.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("v1/persons/{id}")
    public PersonV1 getPersonV1(@PathVariable Integer id) {
        return new PersonV1("Test Subject");
    }

    @GetMapping("v2/persons/{id}")
    public PersonV2 getPersonV2(@PathVariable Integer id) {
        return new PersonV2(new Name("Test", "Subject"));
    }
}
