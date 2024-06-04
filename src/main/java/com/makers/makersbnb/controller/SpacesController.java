package com.makers.makersbnb.controller;

import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class SpacesController {
    @Autowired
    private SpaceRepository spaceRepository;

    @GetMapping("/spaces")
    public ModelAndView index(@AuthenticationPrincipal OAuth2User principal) {
        // getAttributes
        Map attributes = principal.getAttributes();
        // they will then be printed when you go to "/spaces"
        System.out.println(attributes);

        ModelAndView modelAndView = new ModelAndView("/spaces/index");
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
