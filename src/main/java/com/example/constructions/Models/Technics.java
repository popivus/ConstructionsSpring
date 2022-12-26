package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Technics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    private String name;

    @ManyToOne
    private Country country;

    @ManyToMany
    @JoinTable(name = "technics_object",
            joinColumns = @JoinColumn(name = "technics_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id"))
    private List<Object> objects;

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Technics(String name, Country country, List<Object> objects) {
        this.name = name;
        this.country = country;
        this.objects = objects;
    }

    public Technics() {
    }
}
