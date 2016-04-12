package com.zooplus.forex.controller;

import com.zooplus.forex.model.CurrencyEnum;
import com.zooplus.forex.model.CurrencyQuery;
import com.zooplus.forex.model.ForexUser;
import com.zooplus.forex.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class CurrencyConversionController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showEmptyForm(Model model) {
        setupConversionForm(model);

        return "consult";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String calculate(
            @Valid CurrencyQuery currencyQuery, BindingResult bindingResult,
            Model model
    ) {
        setupConversionForm(model);

        // calculate conversion result
        currencyQuery.setConvertedAmount(0.);

        // save query in the history
        ForexUser user = userRepository.findByLogin(getLoginName()).get(0);
        currencyQuery.setUser(user);
        user.getQueries().add(currencyQuery);
        currencyQuery.setTimestamp(new Date());
        userRepository.save(user);

        model.addAttribute("lastQueryResult", currencyQuery.toShortString());

        return "consult";
    }

    private void setupConversionForm(Model model) {
        model.addAttribute("login", getLoginName());
        model.addAttribute("supportedCurrencies", CurrencyEnum.values());

        CurrencyQuery predefinedQuery = new CurrencyQuery();
        predefinedQuery.setAmount(100.);
        model.addAttribute("currencyQuery", predefinedQuery);
    }

    private String getLoginName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
