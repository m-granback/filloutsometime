package com.example.filloutsometime.repositories;

import com.example.filloutsometime.entities.ImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository extends JpaRepository<ImageMeta, Long> {
}
