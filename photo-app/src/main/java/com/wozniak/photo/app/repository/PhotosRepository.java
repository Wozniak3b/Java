package com.wozniak.photo.app.repository;

import com.wozniak.photo.app.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
