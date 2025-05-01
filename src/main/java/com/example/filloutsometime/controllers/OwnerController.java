package com.example.filloutsometime.controllers;

import com.example.filloutsometime.entities.Owner;
import com.example.filloutsometime.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://192.168.1.172:80", "http://localhost:80", "http://127.0.0.1:80", "http://192.168.1.172:8080", "http://localhost", "http://localhost:80","http://192.168.1.85"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/all")
    public ResponseEntity<List<Owner>> getAllHorses(){
        return ResponseEntity.ok(ownerService.getAllOwners());
    }
}
