package com.wozniak.photo.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//We have to give it in uppercase
@Table("PHOTOS")
public class Photo {

    @Id
    private Integer id;

    //It changes by default the fileName into file_name so we have to match it in schema.sql
    @NotEmpty
    private String fileName;

    private String contentType;

    //By this we wont se the long base64 decoded data string
    @JsonIgnore
    private byte[] data;

    public Photo() {
    }

    //public Photo(String id, String fileName) {
    //    this.id = id;
    //    this.fileName = fileName;
    //}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
