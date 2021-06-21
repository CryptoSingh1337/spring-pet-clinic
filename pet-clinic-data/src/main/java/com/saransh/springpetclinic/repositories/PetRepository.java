package com.saransh.springpetclinic.repositories;

import com.saransh.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CryptoSingh1337 on 6/21/2021
 */

public interface PetRepository extends CrudRepository<Pet, Long> {
}
