package com.lopez.rafael.restfulwebservice.controllers;

import com.lopez.rafael.restfulwebservice.models.Name;
import com.lopez.rafael.restfulwebservice.models.PersonV1;
import com.lopez.rafael.restfulwebservice.models.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    //http://localhost:8080/v1/persons/1
    @GetMapping("v1/persons/{id}")
    public PersonV1 getPersonV1(@PathVariable Integer id) {
        return new PersonV1("Test Subject");
    }

    //http://localhost:8080/v2/persons/1
    @GetMapping("v2/persons/{id}")
    public PersonV2 getPersonV2(@PathVariable Integer id) {
        return new PersonV2(new Name("Test", "Subject"));
    }

    // *** Another approach using params***

    //http://localhost:8080/persons/1/param?version=1
    @GetMapping(value = "persons/{id}/param", params = "version=1")
    public PersonV1 params1(@PathVariable Integer id) {
        return new PersonV1("Test Subject");
    }

    //http://localhost:8080/persons/1/param?version=2
    @GetMapping(value = "persons/{id}/param", params = "version=2")
    public PersonV2 params2(@PathVariable Integer id) {
        return new PersonV2(new Name("Test", "Subject"));
    }

    // *** Another approach using headers ***
    // (You need to include the X-API-VERSION header in the request)

    //http://localhost:8080/persons/1/header
    @GetMapping(value = "persons/{id}/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(@PathVariable Integer id) {
        return new PersonV1("Test Subject");
    }

    //http://localhost:8080/persons/1/header
    @GetMapping(value = "persons/{id}/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(@PathVariable Integer id) {
        return new PersonV2(new Name("Test", "Subject"));
    }
}
