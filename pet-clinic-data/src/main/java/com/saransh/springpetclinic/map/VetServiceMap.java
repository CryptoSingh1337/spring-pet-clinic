package com.saransh.springpetclinic.map;

import com.saransh.springpetclinic.model.Vet;
import com.saransh.springpetclinic.services.VetService;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
