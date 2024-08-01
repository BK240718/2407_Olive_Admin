package com.BK._OliveStaff.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgUploadService {

    String upload(MultipartFile img);

    boolean deteleImg(String url);
}
