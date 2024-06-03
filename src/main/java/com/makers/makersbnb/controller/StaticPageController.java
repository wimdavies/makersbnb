package com.makers.makersbnb.controller;

import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StaticPageController {
    @Autowired
    private SpaceRepository spaceRepository;

    @GetMapping("/")
    public ModelAndView landingPage() {
        ModelAndView modelAndView = new ModelAndView("/LandingPage");
        long nSpaces = spaceRepository.count();
        modelAndView.addObject("nSpaces", nSpaces);
        Integer nBookings = 123;
        modelAndView.addObject("nBookings", nBookings);
        String[] reviews = new String[] {"Awesome", "Nice", "Perfect"};
        modelAndView.addObject("reviews", reviews);
        return modelAndView;
    }

    @GetMapping("/spaces")
    public ModelAndView allSpaces() {
        ModelAndView modelAndView = new ModelAndView("/spaces/list");
        Iterable<Space> spaces = spaceRepository.findAll();
        modelAndView.addObject("spaces", spaces);
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
