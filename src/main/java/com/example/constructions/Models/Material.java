package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    private String name;

    @NotNull
    @Min(message = "Количество материалов должно быть положительным", value = 0)
    private int amount;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Materials_Object> materials_objects;

    public Material() {
    }

    public Material(String name, int amount, List<Materials_Object> materials_objects) {
        this.name = name;
        this.amount = amount;
        this.materials_objects = materials_objects;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Materials_Object> getMaterials_objects() {
        return materials_objects;
    }

    public void setMaterials_objects(List<Materials_Object> materials_objects) {
        this.materials_objects = materials_objects;
    }
}
