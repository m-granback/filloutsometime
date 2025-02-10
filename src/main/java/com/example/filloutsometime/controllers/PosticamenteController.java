package com.example.filloutsometime.controllers;

import com.example.filloutsometime.entities.ImageMeta;
import com.example.filloutsometime.repositories.MetaDataRepository;
import com.example.filloutsometime.services.FileService;
import com.example.filloutsometime.services.PosticamenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = {"http://192.168.1.172:80", "http://localhost:80", "http://127.0.0.1:80", "http://localhost"})
public class PosticamenteController {

    @Autowired
    private MetaDataRepository metaDataRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private PosticamenteService posticamenteService;

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
    @PostMapping("/imageandmeta")
    public ResponseEntity<String> uploadImageAndMeta(@RequestParam("file") MultipartFile file, @RequestParam("metadata") String imageMetaDataJson) throws IOException {
        String uniqueFileName = posticamenteService.generateUUID();
        String fileExtension = "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ImageMeta metadata = objectMapper.readValue(imageMetaDataJson, ImageMeta.class);
            metadata.setFileName(uniqueFileName + fileExtension);
            String uploadDir = "images/";
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            System.err.println(metadata.getFileName()+file.getContentType());
            Path filePath = uploadPath.resolve(metadata.getFileName());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            metaDataRepository.save(metadata);
            System.out.println("Metadata: " + metadata);
        } catch (IOException | NullPointerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed \n" + e.getMessage());
        }

        return ResponseEntity.ok("File upload successfully!");
    }
    @GetMapping("/list")
    public ResponseEntity<List<ImageMeta>> getMetaData(){
        return ResponseEntity.ok(fileService.getAllMetaData(false));
    }
    @GetMapping("/list/alphabetical")
    public ResponseEntity<List<ImageMeta>> getMetaDataAlphabetical(){
        return ResponseEntity.ok(fileService.getAllMetaDataAlphabetical(false));
    }
    @GetMapping("/list/rating")
    public ResponseEntity<List<ImageMeta>> getMetaDataByRating(){
        return ResponseEntity.ok(fileService.getAllMetaDataByRating(false));
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) throws IOException {
        ImageMeta imageMeta = metaDataRepository.findById(id).get();
        String uploadDir = "images/";
        Path path = Paths.get(uploadDir + imageMeta.getFileName());
        System.out.println(path.toFile().getAbsolutePath());
//        BufferedImage image = ImageIO.read(path.toFile());
        byte[] imageBytes = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType;
        if(imageMeta.getFileName().endsWith(".png")){
            mediaType = MediaType.IMAGE_PNG;
        } else if(imageMeta.getFileName().endsWith(".jpg") || imageMeta.getFileName().endsWith(".jpeg")){
            mediaType = MediaType.IMAGE_JPEG;
        } else {
            return null;
        }
        headers.setContentType(mediaType);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
