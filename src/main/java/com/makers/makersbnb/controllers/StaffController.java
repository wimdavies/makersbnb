package com.makers.makersbnb.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    @GetMapping("/team")
    public String index() {
        return "Toby, Katerina, Sandy";
    }
}
