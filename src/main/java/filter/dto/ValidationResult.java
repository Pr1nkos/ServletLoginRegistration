package filter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ValidationResult {
    private ErrorMessages errorsForInput;
}
