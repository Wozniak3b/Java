package com.wozniak.photo.app.web;

import com.wozniak.photo.app.model.Photo;
import com.wozniak.photo.app.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotoController {

    //We can do Autowired instead of this injection which would not require this constructor underneath
    private final PhotosService photosService;

    public PhotoController(PhotosService photosService) {
        this.photosService = photosService;
    }

    // private List<Photo> db = List.of(new Photo("1", "hello.jpg"));

    @GetMapping("/")
    public String hello() {
        return "Hello Traveler";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get() { return photosService.get();}

    @GetMapping("/photos/{id}")
    // Path Variable -> This tells that if you have in link id get it and use it
    public Photo get(@PathVariable Integer id) {
        Photo photo = photosService.get(id);

        if (photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/photos")
    public ResponseEntity<Photo> create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = photosService.save(file.getOriginalFilename(),file.getContentType(), file.getBytes());
        return new ResponseEntity<>(photo, HttpStatus.CREATED);
    }
}

// end at 37min
