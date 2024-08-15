package service.validation;

import filter.dto.ErrorMessages;
import filter.dto.ValidationResult;

import java.io.IOException;
import java.util.List;

public interface Validator {
    ErrorMessages validate (String username) throws IOException;

    String getInputName();
}
