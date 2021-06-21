package com.saransh.springpetclinic.services.map;

import com.saransh.springpetclinic.model.Visit;
import com.saransh.springpetclinic.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/21/2021
 */
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements CrudService<Visit, Long> {

    @Override
    public Visit save(Visit object) {
        if (object.getPet() == null || object.getPet().getId() == null || object.getPet().getOwner() == null)
            throw new RuntimeException("Invalid Visit");
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
