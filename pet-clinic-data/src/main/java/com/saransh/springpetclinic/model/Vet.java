package com.saransh.springpetclinic.model;

import java.util.Set;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class Vet extends Person {

    private Set<Speciality> specialities;

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
