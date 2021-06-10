package com.saransh.springpetclinic.model;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class PetType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
