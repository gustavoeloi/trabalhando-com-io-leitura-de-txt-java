package model.entities;

import java.io.Serializable;

public class Autor implements Serializable {
    private String name;

    public Autor(String nome) {
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
