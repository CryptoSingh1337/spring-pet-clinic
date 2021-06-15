package com.saransh.springpetclinic.model;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class Owner extends Person {

    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
