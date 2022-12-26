package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(message = "Площадь должна быть больше 0", value = 1)
    private Double area;

    @NotEmpty(message = "Заполните поле")
    private String country, city, street;

    @OneToMany(mappedBy = "sector", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Sector() {
    }

    public Sector(Double area, String country, String city, String street, List<Object> objects) {
        this.area = area;
        this.country = country;
        this.city = city;
        this.street = street;
        this.objects = objects;
    }
}
