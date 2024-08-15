package servlet.auth.helper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Credential {
    private String username;
    private String password;
}
