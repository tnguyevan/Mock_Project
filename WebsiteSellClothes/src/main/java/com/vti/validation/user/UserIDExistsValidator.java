package com.vti.validation.user;

import com.vti.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIDExistsValidator implements ConstraintValidator<UserIDExists, Integer> {

    @Autowired
    private IUserService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(id)) {
            return true;
        }

        return service.existsUserById(id);
    }
}

