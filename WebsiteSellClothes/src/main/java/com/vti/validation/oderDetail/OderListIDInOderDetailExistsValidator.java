package com.vti.validation.oderDetail;

import com.vti.service.implement.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OderListIDInOderDetailExistsValidator implements ConstraintValidator<OderListIDInOderDetailExists, Integer> {

    @Autowired
    private IOderDetailService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(Integer oderId, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(oderId)) {
            return true;
        }

        return service.existsOderDetailByOderListId(oderId);
    }
}

