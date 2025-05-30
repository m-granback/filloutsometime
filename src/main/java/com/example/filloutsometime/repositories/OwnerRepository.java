package com.example.filloutsometime.repositories;

import com.example.filloutsometime.entities.Horse;
import com.example.filloutsometime.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
