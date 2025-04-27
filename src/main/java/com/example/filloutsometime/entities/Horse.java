package com.example.filloutsometime.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate birth;
    private String shoeSize;
    private LocalDate lastShod;

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
    public int age(){
        return 99;
    }
}
