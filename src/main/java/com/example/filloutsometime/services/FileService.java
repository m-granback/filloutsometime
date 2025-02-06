package com.example.filloutsometime.services;

import com.example.filloutsometime.entities.ImageMeta;
import com.example.filloutsometime.repositories.MetaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
            if(checkIfImageExists(imageMeta.getDescription())){
                validImageMetas.add(imageMeta);
            }
        }
        return validImageMetas;
    }
    private boolean checkIfImageExists(String imageName) {
        File file = new File("images/" + imageName);
        return file.exists();
    }
}
