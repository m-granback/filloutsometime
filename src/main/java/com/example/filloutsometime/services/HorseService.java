package com.example.filloutsometime.services;

import com.example.filloutsometime.entities.Horse;
import com.example.filloutsometime.repositories.HorseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HorseService {
    private HorseRepository horseRepository;

    public HorseService(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public Horse setNewShowDate(int id, LocalDate date){
        Horse horseToUpdate = horseRepository.findById(id).get();
        horseToUpdate.setLastShod(date);
        return horseRepository.save(horseToUpdate);
    }
}
