package com.saransh.springpetclinic.services.springdatajpa;

import com.saransh.springpetclinic.model.PetType;
import com.saransh.springpetclinic.repositories.PetTypeRepository;
import com.saransh.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/21/2021
 */
@Service
@Profile("spring_data_jpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public PetType findById(Long id) {
        Optional<PetType> petType = petTypeRepository.findById(id);
        return petType.orElse(null);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
