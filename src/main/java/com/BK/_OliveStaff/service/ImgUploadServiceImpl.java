package com.BK._OliveStaff.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ImgUploadServiceImpl implements ImgUploadService {

    private final AmazonS3Client s3Client;

    @Value("${aws.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile img) {

        System.out.println("ImgUploadServiceImpl upload Start");

        // 업로드할 파일 이름 변경
        String originFileName = img.getOriginalFilename();
        String fileName = changeFileName(originFileName);

        // S3에 업로드할 파일의 메타데이터 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(img.getContentType());
        metadata.setContentLength(img.getSize());

        try {
            // S3에 파일 업로드
            s3Client.putObject(bucket, fileName, img.getInputStream(), metadata);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 업로드한 파일의 S3 URL 주소 반환
        return s3Client.getUrl(bucket, fileName).toString();
    }

    private String changeFileName(String originFileName) {
        // 업로드할 파일 이름 변경
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return originFileName + "_" + LocalDateTime.now().format(formatter);
    }
}
