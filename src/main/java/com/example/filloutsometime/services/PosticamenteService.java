package com.example.filloutsometime.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PosticamenteService {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
