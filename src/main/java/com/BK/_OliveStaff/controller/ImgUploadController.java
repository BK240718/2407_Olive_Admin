package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.service.ImgUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ImgUploadController {

    private final ImgUploadService imgUploadService;

    @RequestMapping("/uploadForm")
    public String uploadForm(Model model) {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("img") MultipartFile img,
                         Model model) {

        System.out.println("ImgUploadService upload Start");

        // 이미지 업로드
        String imgUrl = imgUploadService.upload(img);
        System.out.println("imgUrl = " + imgUrl);

        // View에게 전달할 데이터를 Model에 담음
        model.addAttribute("imgUrl", imgUrl);

        return "img";
    }
}
