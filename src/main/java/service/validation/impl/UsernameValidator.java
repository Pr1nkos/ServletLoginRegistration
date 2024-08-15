package service.validation.impl;

import filter.dto.ErrorMessages;
import filter.dto.ValidationResult;
import lombok.RequiredArgsConstructor;
import model.User;
import service.UserService;
import service.validation.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class UsernameValidator implements Validator {

    private final UserService userService;

    public ErrorMessages validate (String username) throws IOException {
        List<String> errors = new ArrayList<>();
        boolean isExist = userService.getAllUsers().stream()
                .map(User::getName)
                .anyMatch(s -> Objects.equals(s, username)); // Используйте Objects.equals для безопасного сравнения
        if(isExist) {
            errors.add("Username is already in use");
        }

        if(username.length() < 5){
            errors.add("Username is too short");
        }

        if(username.contains(" ")){
            errors.add("Username contains spaces");
        }

        return new ErrorMessages("loginValidationErrors", errors);
    }

    @Override
    public String getInputName() {
        return "login";
    }
}
