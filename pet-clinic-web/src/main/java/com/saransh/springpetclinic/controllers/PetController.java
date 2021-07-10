package com.saransh.springpetclinic.controllers;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.model.Pet;
import com.saransh.springpetclinic.model.PetType;
import com.saransh.springpetclinic.services.OwnerService;
import com.saransh.springpetclinic.services.PetService;
import com.saransh.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by CryptoSingh1337 on 7/9/2021
 */
@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;

    public PetController(PetTypeService petTypeService,
                         OwnerService ownerService,
                         PetService petService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner populateOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder
    public void initDataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView showAddForm(Owner owner) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        Pet pet = Pet.builder().build();
        pet.setOwner(owner);
        owner.getPets().add(pet);
        mav.addObject("pet", pet);
        return mav;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner,
                                      @Valid Pet pet,
                                      BindingResult result,
                                      Model model) {
        if (StringUtils.hasLength(pet.getName()) &&
                pet.isNew() &&
                owner.getPet(pet.getName(), true) != null)
            result.rejectValue("name", "Duplicate", "already exists");

        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/{petId}/edit")
    public ModelAndView showEditForm(@PathVariable Long petId) {
        ModelAndView mav = new ModelAndView("pets/createOrUpdatePetForm");
        mav.addObject("pet", petService.findById(petId));
        return mav;
    }

    @PostMapping("/{petId}/edit")
    public String processUpdateForm(Owner owner,
                                    @Valid Pet pet,
                                    BindingResult result,
                                    Model model
                                    ) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pets/createOrUpdatePetForm";
        }
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
