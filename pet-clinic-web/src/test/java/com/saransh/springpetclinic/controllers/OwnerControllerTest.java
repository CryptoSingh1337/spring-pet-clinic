package com.saransh.springpetclinic.controllers;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService service;
    @InjectMocks
    private OwnerController controller;
    private Set<Owner> owners;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(
                get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));
    }

    @Test
    void listOwnersByIndex() throws Exception {
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(
                get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners", hasSize(2)))
                .andExpect(view().name("owners/index"));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(view().name("notimplemented"));

        verifyNoInteractions(service);
    }
}