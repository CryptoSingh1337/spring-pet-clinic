package com.saransh.springpetclinic.bootstrap;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.model.Vet;
import com.saransh.springpetclinic.services.OwnerService;
import com.saransh.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by CryptoSingh1337 on 6/11/2021
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        populateOwners();
        populateVets();
    }

    private void populateOwners() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Abhishek");
        owner1.setLastName("Sharma");

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Doe");

        ownerService.save(owner1);
        ownerService.save(owner2);
        System.out.println("Owners Loaded...");
    }

    private void populateVets() {
        Vet vet1 = new Vet();
        vet1.setFirstName("Micheal");
        vet1.setLastName("Thomson");

        Vet vet2 = new Vet();
        vet2.setFirstName("Benjamin");
        vet2.setLastName("Franklin");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("Vets Loaded...");
    }
}
