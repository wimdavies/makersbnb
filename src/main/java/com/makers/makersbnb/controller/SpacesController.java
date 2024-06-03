package com.makers.makersbnb.controller;

import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SpacesController {
    @Autowired
    private SpaceRepository spaceRepository;

    @GetMapping("/spaces")
    public ModelAndView allSpaces() {
        ModelAndView modelAndView = new ModelAndView("/spaces/list");
        Iterable<Space> spaces = spaceRepository.findAll();
        modelAndView.addObject("spaces", spaces);
        return modelAndView;
    }

    @GetMapping("/spaces/new")
    public ModelAndView newSpace() {
        Space space = new Space();
        ModelAndView modelAndView = new ModelAndView("/spaces/new");
        modelAndView.addObject("space", space);
        return modelAndView;
    }

    @PostMapping("/spaces")
    // Spring Boot uses the form data to createSpace an instance of space
    // which is then passed in as an arg here
    public RedirectView createSpace(Space space) {
        spaceRepository.save(space);
        // assumes you already created a method to handle `GET "/spaces"`
        return new RedirectView("/spaces");
    }

}
