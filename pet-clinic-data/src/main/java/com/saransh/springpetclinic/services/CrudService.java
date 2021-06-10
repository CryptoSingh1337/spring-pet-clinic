package com.saransh.springpetclinic.services;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface CrudService<T, ID> {

    T save(T object);
    T findById(ID id);
    Set<T> findAll();
    void delete(T object);
    void deleteById(ID id);
}
