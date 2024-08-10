package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type User dao.
 */
@RequiredArgsConstructor
public class UserDAO {

    private final ObjectMapper mapper;
    private final File file;

    /**
     * Save.
     *
     * @param user the user
     * @throws IOException the io exception
     */
    public void save(User user) throws IOException {
        List<User> users = findAll();
        users.add(user);
        mapper.writeValue(file, users);
    }

    /**
     * Find all list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public List<User> findAll() throws IOException {
        return mapper.readValue(file, new TypeReference<>() {
        });
    }
}