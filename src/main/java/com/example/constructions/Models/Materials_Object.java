package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Materials_Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Object object;

    @Min(message = "Необходимо выделить хотя бы одну единицу материала", value = 1)
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Materials_Object(Material material, Object object, int amount) {
        this.material = material;
        this.object = object;
        this.amount = amount;
    }

    public Materials_Object(){
    }
}
