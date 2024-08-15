package service.validation;

import filter.dto.ErrorMessages;
import filter.dto.ValidationResult;
import lombok.RequiredArgsConstructor;
import servlet.auth.helper.dto.Credential;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ValidationExecutor {
    private final Map<String, Validator> validators;

    public List<ErrorMessages> execute (Map<String, String> map) throws IOException {
        List<ErrorMessages> validationResults = new ArrayList<>();

        for(Map.Entry<String, Validator> entry : validators.entrySet()) {
            for(Map.Entry<String, String> param : map.entrySet()) {
                if(param.getKey().equals(entry.getKey())) {
                    Validator value = entry.getValue();
                    ErrorMessages validate = value.validate(param.getValue());
                    validationResults.add(validate);
                }
            }
        } return validationResults;
    }
}
