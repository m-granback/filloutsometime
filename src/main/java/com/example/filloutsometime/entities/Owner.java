package com.example.filloutsometime.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String home;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Horse> listOfHorses;

    public Owner() {
    }

    public Owner(String firstName, String lastName, String home) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.home = home;
    }

    public int getNumberOfHorses(){
        return listOfHorses.size();
    }
    public int getNumberOfHorsesWithinSixWeeksNotice(){
        List<Horse> horsesGettingUrgent = new ArrayList<>();
        for (Horse horse :
                listOfHorses) {
            LocalDate horseLastShod = horse.getLastShod();
            LocalDate today = LocalDate.now();
            if(horseLastShod.isBefore(today.minusWeeks(6))){
                horsesGettingUrgent.add(horse);
            }
        }
        return horsesGettingUrgent.size();
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
