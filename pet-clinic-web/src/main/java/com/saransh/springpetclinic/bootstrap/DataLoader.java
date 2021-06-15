package com.saransh.springpetclinic.bootstrap;

import com.saransh.springpetclinic.model.Owner;
import com.saransh.springpetclinic.model.Pet;
import com.saransh.springpetclinic.model.PetType;
import com.saransh.springpetclinic.model.Vet;
import com.saransh.springpetclinic.services.OwnerService;
import com.saransh.springpetclinic.services.PetTypeService;
import com.saransh.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by CryptoSingh1337 on 6/11/2021
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        populateOwners(dog, cat);
        populateVets();
    }

    private void populateOwners(PetType dog, PetType cat) {
        Owner owner1 = new Owner();
        owner1.setFirstName("Abhishek");
        owner1.setLastName("Sharma");
        owner1.setAddress("54 L.South");
        owner1.setCity("Los Angeles");
        owner1.setTelephone("9876543210");

        Pet abhishekPet = new Pet();
        abhishekPet.setName("Rocky");
        abhishekPet.setPetType(dog);
        abhishekPet.setOwner(owner1);
        abhishekPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(abhishekPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("John");
        owner2.setLastName("Doe");
        owner2.setAddress("25 L.West");
        owner2.setCity("Los Angeles");
        owner2.setTelephone("0123456789");

        Pet johnPet = new Pet();
        johnPet.setName("Timmy");
        johnPet.setPetType(cat);
        johnPet.setOwner(owner2);
        johnPet.setBirthDate(LocalDate.now().minusYears(5));
        owner2.getPets().add(johnPet);

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
