package com.example.filloutsometime.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birth;
    private String shoeSize;
    private LocalDate lastShod;
    @ManyToOne
    @JsonBackReference
    private Owner owner;

    public Horse() {
    }

    public Horse(String name, LocalDate birth, String shoeSize) {
        this.name = name;
        this.birth = birth;
        this.shoeSize = shoeSize;
    }

    public Horse(String name, LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public Horse(String name, LocalDate birth, Owner owner) {
        this.name = name;
        this.birth = birth;
        this.owner = owner;
    }

    public Horse(String name, LocalDate birth, String shoeSize, Owner owner) {
        this.name = name;
        this.birth = birth;
        this.shoeSize = shoeSize;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public LocalDate getLastShod() {
        return lastShod;
    }

    public void setLastShod(LocalDate lastShod) {
        this.lastShod = lastShod;
    }
    public int getAge(){
        return Period.between(this.birth, LocalDate.now()).getYears();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
