package com.makers.makersbnb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StaticPageController {
    @GetMapping("/")
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView("/LandingPage");
        Integer nSpaces = 100;
        modelAndView.addObject("nSpaces", nSpaces);
        Integer nBookings = 123;
        modelAndView.addObject("nBookings", nBookings);
        String[] reviews = new String[] {"Awesome", "Nice", "Perfect"};
        modelAndView.addObject("reviews", reviews);
        return modelAndView;
    }

    @GetMapping("/contactus")
    public String contactUs() {
        return "email@website.com";
    }

    @GetMapping("/termsandconditions")
    public String termsAndConditions() {
        return "Coming soon! In the meantime, please behave yourselves.";
    }

}