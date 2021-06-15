package com.saransh.springpetclinic.model;

/**
 * Created by CryptoSingh1337 on 6/15/2021
 */
public class Speciality extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
