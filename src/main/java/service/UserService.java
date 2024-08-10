package service;

import dao.UserDAO;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.IOException;
import java.util.List;

/**
 * The type User service.
 */
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    /**
     * Save user.
     *
     * @param user the user
     * @throws IOException the io exception
     */
    public void saveUser(User user) throws IOException {
        userDAO.save(user);
    }

    /**
     * Gets all users.
     *
     * @return the all users
     * @throws IOException the io exception
     */
    public List<User> getAllUsers() throws IOException {
        return userDAO.findAll();
    }
}