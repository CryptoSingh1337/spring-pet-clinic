package com.saransh.springpetclinic.map;

import com.saransh.springpetclinic.model.Pet;
import com.saransh.springpetclinic.services.CrudService;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
