package com.zooplus.forex;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("login", auth.getName());

        return "consult";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}