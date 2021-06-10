package com.saransh.springpetclinic.map;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
