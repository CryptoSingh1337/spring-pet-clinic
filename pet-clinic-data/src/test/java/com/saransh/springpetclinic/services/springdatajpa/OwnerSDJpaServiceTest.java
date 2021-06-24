package com.saransh.springpetclinic.services.springdatajpa;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.repositories.OwnerRepository;
import com.saransh.springpetclinic.repositories.PetRepository;
import com.saransh.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public final String LAST_NAME = "Doe";
    public final long ID = 1L;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTypeRepository petTypeRepository;
    @InjectMocks
    private OwnerSDJpaService service;
    private Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(ID).build();
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner owner = service.save(ownerToSave);
        assertNotNull(service.save(owner));
        assertEquals(ID, owner.getId());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner owner = service.findById(1L);
        assertNotNull(owner);
        assertEquals(ID, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(3L);
        assertNull(owner);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
        Owner retrievedOwner = service.findByLastName(LAST_NAME);

        assertNotNull(retrievedOwner);
        assertEquals(ID, retrievedOwner.getId());
        assertEquals(LAST_NAME, retrievedOwner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnedSet = new HashSet<>();
        returnedSet.add(Owner.builder().id(1L).build());
        returnedSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnedSet);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void delete() {
        service.delete(returnedOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}