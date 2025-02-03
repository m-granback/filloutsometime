package com.example.filloutsometime.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = {"http://192.168.1.172:4200", "http://localhost:4200"})
public class PosticamenteController {
    @PostMapping("/image")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        System.out.println("Something going on...");
        String uploadDir = new File("images").getAbsolutePath();
        File directory = new File(uploadDir);
        if(!directory.exists()){
            directory.mkdir();
        }
        File destinationFile = new File(uploadDir + File.separator + file.getOriginalFilename());
        System.out.println(destinationFile.getAbsolutePath());
        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Image uploaded successfully!");
        response.put("fileName", file.getOriginalFilename());
        response.put("fileSize", String.valueOf(file.getSize()));
        response.put("uploadDir", uploadDir);
        return ResponseEntity.ok(response);
    }
}
