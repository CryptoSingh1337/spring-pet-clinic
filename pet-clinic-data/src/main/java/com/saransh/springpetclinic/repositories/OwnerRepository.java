package com.saransh.springpetclinic.repositories;

import com.saransh.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CryptoSingh1337 on 6/21/2021
 */

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
