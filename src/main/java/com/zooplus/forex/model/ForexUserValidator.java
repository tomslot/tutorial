package com.zooplus.forex.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ForexUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ForexUser.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ForexUser user = (ForexUser) o;

        if (!user.getPassword().equals(user.getRepeatedPassword())){
            errors.rejectValue("repeatedPassword", "password.mismatch");
        }
    }
}
