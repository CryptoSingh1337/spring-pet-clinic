package com.saransh.springpetclinic.services.springdatajpa;

import com.saransh.springpetclinic.model.Vet;
import com.saransh.springpetclinic.repositories.SpecialityRepository;
import com.saransh.springpetclinic.repositories.VetRepository;
import com.saransh.springpetclinic.services.VetService;
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
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final SpecialityRepository specialityRepository;

    public VetSDJpaService(VetRepository vetRepository, SpecialityRepository specialityRepository) {
        this.vetRepository = vetRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet findById(Long id) {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet.orElse(null);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
