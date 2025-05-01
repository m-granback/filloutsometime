package com.example.filloutsometime.services;

import com.example.filloutsometime.entities.Owner;
import com.example.filloutsometime.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }
}
