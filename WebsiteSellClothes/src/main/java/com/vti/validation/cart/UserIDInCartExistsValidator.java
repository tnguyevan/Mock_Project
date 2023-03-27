package com.vti.validation.cart;

import com.vti.service.implement.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIDInCartExistsValidator implements ConstraintValidator<UserIDInCartExists, Integer> {

    @Autowired
    private ICartService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer userId, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(userId)) {
            return true;
        }

        return service.existsCartByUserId(userId);
    }
}

