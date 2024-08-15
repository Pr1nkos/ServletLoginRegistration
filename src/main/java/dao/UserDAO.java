package dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * The type User dao.
 */
public class UserDAO extends BaseDAO<User, UUID> {

    public UserDAO(ObjectMapper mapper, File file) {
        super(mapper, file, new TypeReference<>() {});
    }

    @Override
    protected UUID getId(User user) {
        return user.getId();
    }
}