package com.saransh.springpetclinic.services.map;

import com.saransh.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "Doe";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder()
                .id(OWNER_ID)
                .firstName("John").lastName("Doe")
                .build());
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        Owner savedOwner = ownerMapService.save(Owner.builder().id(id).build());
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(OWNER_ID);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        assertNull(ownerMapService.findByLastName("foo"));
    }

    @Test
    void findAll() {
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(OWNER_ID));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(OWNER_ID);
        assertEquals(0, ownerMapService.findAll().size());
    }
}