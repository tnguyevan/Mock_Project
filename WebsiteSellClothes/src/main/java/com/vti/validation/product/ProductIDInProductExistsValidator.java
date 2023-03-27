package com.vti.validation.product;

import com.vti.service.implement.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDInProductExistsValidator implements ConstraintValidator<ProductIDInProductExists, Integer> {

    @Autowired
    private IProductService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(id)) {
            return true;
        }

        return service.existsProductByProductId(id);
    }
}

