package com.zooplus.forex.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;


@Component
public class CurrencyQueryValidator implements Validator {

    private static Date earliestSupportedDate = new Date(946684800000L); // year 2000

    @Override
    public boolean supports(Class<?> aClass) {
        return CurrencyQuery.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CurrencyQuery query = (CurrencyQuery) o;

        if (query.getDate().before(earliestSupportedDate)){
            errors.rejectValue("date", "date.too.early");
        }
    }
}
