package com.example.filloutsometime.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ImageMeta {
    @Id
    @GeneratedValue
    private long id;
    private String fileName;
    private String description;
    private int rating;

    public ImageMeta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ImageMeta{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
