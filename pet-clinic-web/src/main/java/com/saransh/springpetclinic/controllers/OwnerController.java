package com.saransh.springpetclinic.controllers;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
