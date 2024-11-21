package com.wozniak.photo.app.service;

//import org.springframework.stereotype.Component;
import com.wozniak.photo.app.model.Photo;
import com.wozniak.photo.app.repository.PhotosRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

//You can use both
//@Component
@Service
public class PhotosService {

    // It will always appear after refresh because it's manually added
    // And that's not real database, it's stored only till a reload (in-memory db)
    // That's why we edit application.properties
    //private Map<String, Photo> db = new HashMap<>() {
    //    {
    //        put("1", new Photo("1", "hello.jpg"));
    //    }
    //};

    private final PhotosRepository photosRepository;

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    public Iterable<Photo> get() {
        return photosRepository.findAll();
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photosRepository.save(photo);
        return photo;
    }
}
