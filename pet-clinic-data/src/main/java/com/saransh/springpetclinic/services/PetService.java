package com.saransh.springpetclinic.services;

import com.saransh.springpetclinic.model.Pet;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
