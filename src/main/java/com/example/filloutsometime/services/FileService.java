package com.example.filloutsometime.services;

import com.example.filloutsometime.entities.ImageMeta;
import com.example.filloutsometime.repositories.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private MetaDataRepository metaDataRepository;
    public List<ImageMeta> getAllMetaData(boolean includeWhenDeletedImage) {
        List<ImageMeta> uncheckedImageMetas = metaDataRepository.findAll();
        if(includeWhenDeletedImage)
            return uncheckedImageMetas;
        List<ImageMeta> validImageMetas = new ArrayList<>();
        for (ImageMeta imageMeta : uncheckedImageMetas) {
            if(checkIfImageExists(imageMeta.getFileName())){
                validImageMetas.add(imageMeta);
            }
        }
        return validImageMetas;
    }
    private boolean checkIfImageExists(String imageName) {
        File file = new File("images/" + imageName);
        return file.exists();
        /*
        Path path = Paths.get("images/" + imageName);
        return Files.exists(path);
         */
    }

    public List<ImageMeta> getAllMetaDataAlphabetical(boolean b) {
        return getAllMetaData(b).stream().sorted(Comparator.comparing(ImageMeta::getDescription)).collect(Collectors.toList());
    }

    public List<ImageMeta> getAllMetaDataByRating(boolean b) {
        List<ImageMeta> imageMetas = getAllMetaData(b).stream().sorted(Comparator.comparingInt(ImageMeta::getRating)).collect(Collectors.toList());
        Collections.reverse(imageMetas);
        return imageMetas;
    }
}
