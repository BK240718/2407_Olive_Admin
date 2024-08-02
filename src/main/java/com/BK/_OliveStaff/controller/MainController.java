package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.dto.Staff;
import com.BK._OliveStaff.service.SectionService;
import com.BK._OliveStaff.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final SectionService    sectionService;
    private final StaffService      staffService;
    
    // for main 페이지
    @RequestMapping(value = "/")
    public String home() {

        System.out.println("MainController home Start");

        return "common/login";
    }

    // HttpSession: For 클라이언트 - 서버 간 세션 정보 저장
    @RequestMapping(value = "login")
    public String login(@ModelAttribute Staff staffIdPw,
                        HttpSession session,
                        Model model) {

        System.out.println("MainController login Start");

        Staff staff = staffService.selectStaffByLogin(staffIdPw);

        if (staff != null) {
            session.setAttribute("staffId", staff.getStaffId());
            session.setAttribute("staffName", staff.getStaffName());
            session.setAttribute("profileImg", staff.getProfileImg());
            return "redirect:list";
        } else {
            model.addAttribute("loginError", "Invalid Id or Password");
            return "login";
        }
    }

    @RequestMapping(value = "list")
    public String list(Model model) {

        System.out.println("MainController list Start");
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
