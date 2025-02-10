package com.example.filloutsometime.repositories;

import com.example.filloutsometime.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
