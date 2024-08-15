package filter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorMessages {
    private String attributeName;
    private List <String> errors;
}
