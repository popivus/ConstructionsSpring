package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<Technics> technics;

    public List<Technics> getTechnics() {
        return technics;
    }

    public void setTechnics(List<Technics> technics) {
        this.technics = technics;
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

    public Country(String name, List<Technics> technics) {
        this.name = name;
        this.technics = technics;
    }

    public Country() {
    }
}
