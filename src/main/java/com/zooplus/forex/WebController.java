package com.zooplus.forex;

import com.zooplus.forex.persistence.ForexUser;
import com.zooplus.forex.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class WebController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String calculate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("login", auth.getName());

        return "consult";
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