package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Staff;
import com.BK._OliveStaff.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StaffController {

    private final StaffService staffService;



    @RequestMapping(value = "listStaff")
    public String staffList(Staff staff,
                            @RequestParam(value = "currentPage", required = false)
                            String currentPage, Model model) {

        log.info("StaffController listStaff Start");

        // 1. Staff 전체 cnt
        int totalStaff = staffService.totalStaff();


        // 2. Paging 작업



        model.addAttribute("totalStaff", totalStaff);

        return "list";
    }

}
