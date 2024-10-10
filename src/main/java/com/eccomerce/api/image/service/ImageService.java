package com.eccomerce.api.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String imageUpload(MultipartFile file);
}
