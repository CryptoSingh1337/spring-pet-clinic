package com.saransh.springpetclinic.services;

import com.saransh.springpetclinic.model.Owner;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
