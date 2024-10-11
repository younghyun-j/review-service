package com.eccomerce.api.image.service;

import com.eccomerce.api.image.domain.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    private final String IMAGE_BASE_URI = "images/";

    @Override
    public String imageUpload(MultipartFile file) {
        if (isFileEmpty(file)) return null;
        Image image = new Image(file);
        return uploadFile(image);
    }

    private String uploadFile(Image image) {
        return IMAGE_BASE_URI + UUID.randomUUID() + image.getExtension();
    }

    private static boolean isFileEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }

}
