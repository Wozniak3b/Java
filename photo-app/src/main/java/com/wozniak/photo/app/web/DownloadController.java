package com.wozniak.photo.app.web;


import com.wozniak.photo.app.model.Photo;
import com.wozniak.photo.app.service.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        //DEPENDENCY INJECTION -> we create PhotosService to inject the database from PhotoController into Service
        //And use it in here and PhotoController
        Photo photo = photosService.get(id);
        if (photo==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte [] data = photo.getData();
        HttpHeaders headers = new HttpHeaders();

        //By that browser knows which content type we return gif png etc
        headers.setContentType(MediaType.valueOf(photo.getContentType()));

        //This to display photo in browser or download it automatically inline would display, attachment downloads
        ContentDisposition build = ContentDisposition
                .builder("attachment")
                .filename(photo.getFileName())
                .build();
        headers.setContentDisposition(build);

        return new ResponseEntity<>(data,headers, HttpStatus.OK);
    }
}
