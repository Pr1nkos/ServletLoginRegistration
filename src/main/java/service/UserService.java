package service;

import dao.UserDAO;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import servlet.auth.helper.dto.Credential;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type User service.
 */
@RequiredArgsConstructor
public class UserService {


    private final UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    public void saveUser(Credential credential) throws IOException {
        String hashedPassword = passwordEncoder.encode(credential.getPassword());
        userDAO.save(User.builder()
                        .id(UUID.randomUUID())
                        .name(credential.getUsername())
                        .password(hashedPassword)
                .build());
    }

    public List<User> getAllUsers() throws IOException {
        return userDAO.findAll();
    }

    public Optional<User> getExistedUser(Credential credential) throws IOException {
        return getAllUsers().stream()
                .filter(user -> passwordEncoder.matches(credential.getPassword(), user.getPassword()))
                .findFirst();
    }


}