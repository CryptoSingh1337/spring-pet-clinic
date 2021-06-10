package com.saransh.springpetclinic.model;

import java.io.Serializable;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
