package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Item;
import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.service.ItemService;
import com.BK._OliveStaff.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final SectionService sectionService;

    @RequestMapping("showAddItemForm")
    public String showAddItemForm(Model model) {

        System.out.println("ItemController showAddItemForm Start");
        Section section = null;

        List<Section> getSection = sectionService.getSection();

        model.addAttribute("getSection", getSection);

        return "item/writeFormItem";
    }

    @ResponseBody
    @PostMapping(value = "insertItem")
    public Map<String, Object> insertItem(Item item) {

        System.out.println("ItemController insertItem Start");

        Map<String, Object> response = new HashMap<>();
        int insertResult = itemService.insertItem(item);
        if (insertResult > 0) {
            response.put("status", "success");
        } else {
          response.put("status", "error");
          response.put("message", "입력 실패");
        }

        return response;
    }
}
