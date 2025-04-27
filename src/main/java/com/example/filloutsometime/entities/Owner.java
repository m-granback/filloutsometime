package com.example.filloutsometime.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String home;
    @OneToMany
    private List<Horse> listOfHorses;

    public Owner() {
    }

    public Owner(String firstName, String lastName, String home) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.home = home;
    }

    public int getNumberOfHorses(){
        return 99;
    }
    public int getNumberOfHorsesWithinThreeWeekNotice(){
        return 99;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public List<Horse> getListOfHorses() {
        return listOfHorses;
    }

    public void setListOfHorses(List<Horse> listOfHorses) {
        this.listOfHorses = listOfHorses;
    }
}
