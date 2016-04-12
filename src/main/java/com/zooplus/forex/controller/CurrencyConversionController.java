package com.zooplus.forex.controller;

import com.zooplus.forex.model.CurrencyEnum;
import com.zooplus.forex.model.CurrencyQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurrencyConversionController {
    @RequestMapping(value = "/")
    public String calculate(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("login", auth.getName());
        model.addAttribute("supportedCurrencies", CurrencyEnum.values());
        model.addAttribute("currencyQuery", new CurrencyQuery());

        return "consult";
    }
}
