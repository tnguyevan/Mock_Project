package com.vti.validation.comment;

import com.vti.service.implement.ICommentService;
import com.vti.validation.cart.ProductIDInCartExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDInCommentExistsValidator implements ConstraintValidator<ProductIDInCommentExists, Integer> {

    @Autowired
    private ICommentService commentService;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer productId, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(productId)) {
            return true;
        }

        return commentService.existsCommentByProductId(productId);
    }
}

