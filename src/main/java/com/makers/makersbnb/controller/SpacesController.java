package com.makers.makersbnb.controller;

import com.makers.makersbnb.model.Space;
import com.makers.makersbnb.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/spaces/{id}")
    public ModelAndView showSpace(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/spaces/show");
        Optional<Space> space = spaceRepository.findById(id);
        if (space.isPresent()) {
            modelAndView.addObject("space", space.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @PostMapping("/spaces")
    public ModelAndView createSpace(Space space) {
        spaceRepository.save(space);
        return new ModelAndView("redirect:/spaces/");
        // once you're saving the user_id from the user matching the principal in /new,
        // you can change the successful redirect to this:
        // return new ModelAndView("redirect:/spaces/" + space.getId());

        // TODO: add conditional to handle unsuccessful save (redirect back to new)
    }

}
