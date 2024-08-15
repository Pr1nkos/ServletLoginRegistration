package dao;

import annotation.Id;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import model.User;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseDAO<ENTITY, ID extends Serializable> {
    private final ObjectMapper mapper;
    private final File file;

    private final TypeReference<List<ENTITY>> typeRef;


    public void save(ENTITY entity) throws IOException {
        List<ENTITY> entities = findAll();
        entities.add(entity);
        mapper.writeValue(file, entities);
    }

    public List<ENTITY> findAll() throws IOException {
        return mapper.readValue(file, typeRef);
    }

    public void delete(ID id) throws IOException {
        List<ENTITY> entities = findAll();
        List<ENTITY> toDelete = entities.stream()
                .filter(entity -> !getId(entity).equals(id))
                .toList();
        mapper.writeValue(file, toDelete);
    }

    @SuppressWarnings("unchecked")
    protected ID getId(ENTITY entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (ID) field.get(entity);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return null;
                    } finally {
                        field.setAccessible(false);
                    }
                })
                .orElse(null);
    }

}
