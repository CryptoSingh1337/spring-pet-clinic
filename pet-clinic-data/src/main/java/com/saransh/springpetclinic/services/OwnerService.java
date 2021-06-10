package com.saransh.springpetclinic.services;

import com.saransh.springpetclinic.model.Owner;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface OwnerService {

    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
