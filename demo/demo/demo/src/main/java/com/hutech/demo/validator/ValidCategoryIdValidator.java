package com.hutech.demo.validator;

import com.hutech.demo.entity.Category;
import com.hutech.demo.validator.annotation.ValidatorCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidatorCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}
