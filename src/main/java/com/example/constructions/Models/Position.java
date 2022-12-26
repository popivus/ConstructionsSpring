package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    private String name;

    @NotNull
    @Min(message = "Зарплата должна быть больше 0", value = 1)
    private Double salary;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    private List<Employee> employee;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Position(String name, Double salary, List<Employee> employee) {
        this.name = name;
        this.salary = salary;
        this.employee = employee;
    }

    public Position() {
    }
}
