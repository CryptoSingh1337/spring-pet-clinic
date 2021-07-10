package com.saransh.springpetclinic.controllers;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.model.Pet;
import com.saransh.springpetclinic.model.PetType;
import com.saransh.springpetclinic.services.OwnerService;
import com.saransh.springpetclinic.services.PetService;
import com.saransh.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    private OwnerService ownerService;
    @Mock
    private PetService petService;
    @Mock
    private PetTypeService petTypeService;
    @InjectMocks
    private PetController controller;
    private MockMvc mockMvc;
    private Owner owner;
    private Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder()
                .id(1L)
                .pets(new HashSet<>())
                .build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void populatePetTypes() {
    }

    @Test
    void populateOwner() {
    }

    @Test
    void initDataBinder() {
    }

    @Test
    void showAddForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any());
    }

    @Test
    void showEditForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any());
    }
}