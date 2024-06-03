package com.makers.makersbnb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticPageController {
    @GetMapping("/")
    public String landingPage() {
        return "Welcome to MakersBnB!";
    }
}
