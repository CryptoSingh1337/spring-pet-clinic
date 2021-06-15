package com.saransh.springpetclinic.services.map;

import com.saransh.springpetclinic.model.Speciality;
import com.saransh.springpetclinic.services.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/15/2021
 */
@Service
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
