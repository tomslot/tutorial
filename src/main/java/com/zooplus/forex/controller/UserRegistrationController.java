package com.zooplus.forex.controller;

import com.zooplus.forex.model.ForexUser;
import com.zooplus.forex.model.ForexUserValidator;
import com.zooplus.forex.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class UserRegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForexUserValidator userValidator;

    @InitBinder("forexUser")
    protected void initBinder(final WebDataBinder binder)
    {
        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveNewUser(@Valid ForexUser forexUser, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            return "register";
        }

        userRepository.save(Collections.singleton(forexUser));
        model.addAttribute("login", forexUser.getLogin());

        return "user_saved";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute(value = "message") String message, Model model){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        ForexUser user = new ForexUser();
        model.addAttribute("forexUser", user);
        return "register";
    }

}