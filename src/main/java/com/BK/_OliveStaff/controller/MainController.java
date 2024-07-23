package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.service.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final SectionService sectionService;
    
    // for main 페이지
    @RequestMapping(value = "/")
    public String home() {
        return "list";
    }


    @ResponseBody
    @RequestMapping(value = "mainSection", method = RequestMethod.POST)
    public Map<String, Object> mainSection() {

        System.out.println("MainController mainSection Start");

        Map<String, Object> response = new HashMap<String, Object>();
        List<Section> sectionList = sectionService.mainSection();
        response.put("sectionList", sectionList);

        return response;
    }



}
