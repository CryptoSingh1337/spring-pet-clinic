package com.saransh.springpetclinic.services.map;

import com.saransh.springpetclinic.model.BaseEntity;

import java.util.*;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected final Map<Long, T> map = new HashMap<>();
    private static long id;

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null)
                object.setId(getNextID());
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(e -> e.getValue().equals(object));
    }

    private Long getNextID() {
        return map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1;
    }
}
