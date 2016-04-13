package com.zooplus.forex.controller;

import com.zooplus.forex.model.*;
import com.zooplus.forex.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CurrencyConversionController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CurrencyConversionService conversionService;

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
        if (bindingResult.hasErrors()){
            model.addAttribute("supportedCurrencies", CurrencyEnum.values());
            return "consult";
        }

        setupConversionForm(model);

        // calculate conversion result
        conversionService.covert(currencyQuery);

        // save query in the history
        ForexUser user = getUser();
        currencyQuery.setUser(user);
        user.getQueries().add(currencyQuery);
        System.err.println("user.getQueries().size()=" + user.getQueries().size());
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
        predefinedQuery.setSrcCurrency(CurrencyEnum.EUR);
        predefinedQuery.setDstCurrency(CurrencyEnum.USD);
        predefinedQuery.setDate(new Date());
        model.addAttribute("currencyQuery", predefinedQuery);
        Page<CurrencyQuery> previousQueries = userRepository.findLastQueriesForUser(getUser().getId(), new PageRequest(0, 10));
        List<String> renderedQueries = new ArrayList<>();

        for (CurrencyQuery query : previousQueries){
            renderedQueries.add(query.toShortString());
        }

        model.addAttribute("previousQueries", renderedQueries);
    }

    private ForexUser getUser(){
        return userRepository.findByLogin(getLoginName()).get(0);
    }

    private String getLoginName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
