package com.lopez.rafael.restfulwebservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class MessageController {
    private MessageSource messageSource;

    @Autowired
    public MessageController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("hello-world-1")
    public String getMessageOption1(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, "Default Message - Optional", locale);
    }

    @GetMapping("hello-world-2")
    public String getMessageOption2() {
        return messageSource.getMessage( "good.morning.message", null, "Default Message - Optional",
                LocaleContextHolder.getLocale() );
    }
}
