package com.vti.validation.cart;

import com.vti.service.implement.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDInCartExistsValidator implements ConstraintValidator<ProductIDInCartExists, Integer> {

    @Autowired
    private ICartService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer productId, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(productId)) {
            return true;
        }

        return service.existsCartByProductId(productId);
    }
}

