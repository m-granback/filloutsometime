package com.example.filloutsometime.repositories;

import com.example.filloutsometime.entities.Horse;
import com.example.filloutsometime.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorseRepository extends JpaRepository<Horse, Integer> {
    List<Horse> findAllByOrderByLastShodDesc();
    List<Horse> findAllByOwnerIdOrderByLastShodDesc(int id);
}
