package com.example.constructions.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    private String surname;

    @NotEmpty(message = "Заполните поле")
    private String name;

    private String middlename = "";

    @Size(message = "Серия паспорта должна состоять из 4 символов", min = 4, max = 4)
    private String passportSeria;

    @Size(message = "Номер паспорта должен состоять из 6 символов", min = 6, max = 6)
    private String passportNumber;

    @Size(message = "СНИЛС должен состоять из 11 символов", min = 11, max = 11)
    private String SNILS;

    @Size(message = "ИНН должен состоять из 11 символов", min = 12, max = 12)
    private String INN;

    private Boolean fired;

    private Date birthday;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Account account;

    @ManyToOne
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Object> objects;

    @ManyToMany
    @JoinTable(name = "brigade_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "brigade_id"))
    private List<Brigade> brigades;

    public List<Brigade> getBrigades() {
        return brigades;
    }

    public void setBrigades(List<Brigade> brigades) {
        this.brigades = brigades;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSNILS() {
        return SNILS;
    }

    public void setSNILS(String SNILS) {
        this.SNILS = SNILS;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public Boolean getFired() {
        return fired;
    }

    public void setFired(Boolean fired) {
        this.fired = fired;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Employee(String surname, String name, String middlename, String passportSeria, String passportNumber, String SNILS, String INN, Boolean fired, Date birthday, Account account, Position position, List<Object> objects, List<Brigade> brigades) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.passportSeria = passportSeria;
        this.passportNumber = passportNumber;
        this.SNILS = SNILS;
        this.INN = INN;
        this.fired = fired;
        this.birthday = birthday;
        this.account = account;
        this.position = position;
        this.objects = objects;
        this.brigades = brigades;
    }

    public Employee() {
    }
}
