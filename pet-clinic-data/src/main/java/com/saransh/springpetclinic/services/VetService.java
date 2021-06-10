package com.saransh.springpetclinic.services;

import com.saransh.springpetclinic.model.Vet;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
