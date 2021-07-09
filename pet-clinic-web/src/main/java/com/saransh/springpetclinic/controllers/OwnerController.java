package com.saransh.springpetclinic.controllers;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({"", "/"})
    public String processFindOwnerForm(Owner owner,
                                       BindingResult result,
                                       Model model) {
        if (owner.getLastName() == null)
            owner.setLastName("");
        List<Owner> owners = ownerService.findAllByLastNameContainingIgnoreCase(owner.getLastName());
        if (owners.isEmpty()) {
            result.rejectValue("lastName", "Not found", "Not found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            owner = owners.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView displayOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject("owner", ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView showAddOwner() {
        ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
        mav.addObject("owner", Owner.builder().build());
        return mav;
    }

    @PostMapping("/new")
    public String processOwnerCreation(@Valid Owner owner,
                                       BindingResult result) {
        if (result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownerId}/edit")
    public ModelAndView showEditOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/createOrUpdateOwnerForm");
        mav.addObject("owner", ownerService.findById(ownerId));
        return mav;
    }

    @PostMapping("/{ownerId}/edit")
    public String processOwnerEdit(@Valid Owner owner,
                                   BindingResult result,
                                   @PathVariable Long ownerId) {
        if (result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
