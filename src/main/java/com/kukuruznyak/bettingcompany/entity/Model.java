package com.kukuruznyak.bettingcompany.entity;

import java.io.Serializable;

public abstract class Model implements Serializable {
    protected Long id;

    public Model() {
    }

    public Model(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
