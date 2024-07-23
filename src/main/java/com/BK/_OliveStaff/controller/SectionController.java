package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.service.Paging;
import com.BK._OliveStaff.service.SectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SectionController {

    private final SectionService sectionService;

    @RequestMapping(value = "listSection")
    public String sectionList(Section section, @RequestParam(value = "currentPage", required = false) String currentPage, Model model) {

        System.out.println("SectionController listSection Start");

        // 1. Section 전체 Cnt
        int totalSection = sectionService.totalSection();

        // 2. Paging
        Paging page = new Paging(totalSection, currentPage);
        //
        section.setStart(page.getStart());  // 시작 시 1
        section.setEnd(page.getEnd());      // 시작 시 10

        List<Section> listSection = sectionService.listSection(section);
        System.out.println("SectionController sectionList listSection.size()) ="+listSection.size());
        System.out.println("page.getStartPage = " + page.getStartPage());
        System.out.println("currentPage = "+currentPage);

        model.addAttribute("totalSection", totalSection);
        model.addAttribute("listSection", listSection);
        model.addAttribute("page",page);


        return "categories";
    }


    @GetMapping(value = "detailSection")
    public String detailSection(@RequestParam("sectionId") int sectionId, Model model) {

        System.out.println("SectionController detailSection Start");
        System.out.println("sectionId = " + sectionId);

        Section section = sectionService.detailSection(sectionId);

        model.addAttribute("section", section);

        return "detailSection";
    }


    @GetMapping(value = "updateFormSection")
    public String updateFormSection(@RequestParam("sectionId") int sectionId, Model model) {

        System.out.println("SectionController updateFormSection Start");
        System.out.println("sectionId = " + sectionId);

        Section section = sectionService.detailSection(sectionId);

        model.addAttribute("section", section);

        return "updateFormSection";
    }


    @PostMapping(value = "updateSection")
    public String updateSection(Section section, Model model) {

        log.info("SectionController updateSection Start");
        System.out.println("section.getSectionId() = " + section.getSectionId());
        System.out.println("section.getSecName() = " + section.getSecName());

        int updateCount = sectionService.updateSection(section);
        System.out.println("updateCount = " + updateCount);

        return "forward:listSection";
    }


    @RequestMapping(value = "writeFormSection")
    public String writeFormSection(Model model) {
        System.out.println("SectionController writeFormSection Start");

        return "writeFormSection";
    }


    @PostMapping(value = "writeSection")
    public String writeSection(Section section, Model model) {

        System.out.println("SectionController writeFormSection Start");
        System.out.println("section.getSecName() = " + section.getSecName());

        int insertResult = sectionService.insertSection(section);
        if (insertResult > 0) return "redirect:listSection";
        else {
            model.addAttribute("msg", "입력 실패");
            return "forward:writeFormSection";
        }

    }
}
