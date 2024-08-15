package service.validation.impl;

import filter.dto.ErrorMessages;
import filter.dto.ValidationResult;
import service.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordValidator implements Validator {

    public ErrorMessages validate(String password) {

        List<String> errors = new ArrayList<>();

        if (password.length() < 5) {
            errors.add("Password length should be more than 5");
        }

        if (password.contains("@") || password.contains("!")) {
            errors.add("Password contains invalid characters");
        }

        return new ErrorMessages("passwordValidationErrors", errors);
    }
    public String getInputName() {
        return "password";
    }
}
