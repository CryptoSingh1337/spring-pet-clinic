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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    void findOwners() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(service);
    }

    @Test
    void displayOwner() throws Exception {
        when(service.findById(anyLong())).thenReturn(owners.stream().findFirst().get());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFindOwnersFormReturnMany() throws Exception {
        when(service.findAllByLastNameLike(anyString())).thenReturn(new ArrayList<>(owners));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindOwnersFormReturnOne() throws Exception {
        when(service.findAllByLastNameLike(anyString())).thenReturn(List.of(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));
    }
}