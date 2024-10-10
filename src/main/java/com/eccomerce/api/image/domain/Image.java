package com.eccomerce.api.image.domain;

import com.eccomerce.api.exception.CustomException;
import com.eccomerce.api.exception.ExceptionCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Image {

    private final List<String> WHITE_LIST = List.of("jpg", "jpeg", "png", "webp", "heic");
    private final MultipartFile file;


    public Image(MultipartFile file) {
        validate(file);
        this.file = file;
    }

    public void validate(MultipartFile file) {
        validateNotNull(file);
        String fileName = file.getOriginalFilename();
        validateFileNameNotBlank(fileName);
        validateExtension(fileName);
    }

    private void validateNotNull(MultipartFile file) {
        if (file == null) {
            throw new CustomException(ExceptionCode.FILE_NOT_PROVIDED);
        }
    }

    private void validateFileNameNotBlank(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new CustomException(ExceptionCode.BLANK_FILE_NAME);
        }
    }

    private void validateExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex == -1 || fileName.endsWith(".")) {
            throw new CustomException(ExceptionCode.INVALID_FILE_EXTENSION);
        }
        String extension = fileName.substring(extensionIndex + 1);
        if (!WHITE_LIST.contains(extension.toLowerCase())) {
            throw new CustomException(ExceptionCode.UNSUPPORTED_FILE_EXTENSION);
        }
    }

    public String getExtension() {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
