package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    private String objectType;

    @NotNull
    @Min(message = "Площадь должна быть больше 0", value = 1)
    private Double area;

    @NotNull
    @Min(message = "Площадь должна быть больше 0", value = 1)
    private int floors;

    private Date startDate;
    private Date endDate;

    @NotEmpty(message = "Заполните поле")
    private String buildingPermit;

    @NotNull
    private int buildingNumber;

    @ManyToOne
    private Sector sector;

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "object", fetch = FetchType.LAZY)
    private List<Brigade> brigades;

    @ManyToMany
    @JoinTable(name = "technics_object",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "technics_id"))
    private List<Technics> technics;

    @OneToMany(mappedBy = "object", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Materials_Object> materials_objects;

    public Object(String objectType, Double area, int floors, Date startDate, Date endDate, String buildingPermit, int buildingNumber, Sector sector, Employee employee, List<Brigade> brigades, List<Technics> technics, List<Materials_Object> materials_objects) {
        this.objectType = objectType;
        this.area = area;
        this.floors = floors;
        this.startDate = startDate;
        this.endDate = endDate;
        this.buildingPermit = buildingPermit;
        this.buildingNumber = buildingNumber;
        this.sector = sector;
        this.employee = employee;
        this.brigades = brigades;
        this.technics = technics;
        this.materials_objects = materials_objects;
    }

    public Object() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBuildingPermit() {
        return buildingPermit;
    }

    public void setBuildingPermit(String buildingPermit) {
        this.buildingPermit = buildingPermit;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Brigade> getBrigades() {
        return brigades;
    }

    public void setBrigades(List<Brigade> brigades) {
        this.brigades = brigades;
    }

    public List<Technics> getTechnics() {
        return technics;
    }

    public void setTechnics(List<Technics> technics) {
        this.technics = technics;
    }

    public List<Materials_Object> getMaterials_objects() {
        return materials_objects;
    }

    public void setMaterials_objects(List<Materials_Object> materials_objects) {
        this.materials_objects = materials_objects;
    }
}
