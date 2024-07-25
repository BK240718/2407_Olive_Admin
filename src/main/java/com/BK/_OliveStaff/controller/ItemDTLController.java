package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.ItemDTL;
import com.BK._OliveStaff.service.ItemDTLService;
import com.BK._OliveStaff.service.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemDTLController {

    private final ItemDTLService itemDTLService;

    @RequestMapping(value = "listItemDTL")
    public String listItemDTL(ItemDTL itemDTL,
                              @RequestParam(value = "currentPage", required = false) String currentPage,
                              Model model) {

        System.out.println("ItemDTLController listItemDTL Start");

        // 1. ItemDTL 전체 Cnt
        int totalItemDTL = itemDTLService.totalItemDTL();

        // 2. Paging
        Paging page = new Paging(totalItemDTL, currentPage);

        itemDTL.setStart(page.getStart());  // 시작 시 1
        itemDTL.setEnd(page.getEnd());      // 시작 시 10

        List<ItemDTL> listItemDTL = itemDTLService.listItemDTL(itemDTL);
        System.out.println("ItemDTLController listItemDTL listItemDTL.size() = " + listItemDTL.size());

        model.addAttribute("totalItemDTL",totalItemDTL);
        model.addAttribute("listItemDTL",listItemDTL);
        model.addAttribute("page",page);

        return "itemDTL/listItemDTL";
    }


    @RequestMapping(value = "detailItemDTL")
    public String detailItemDTL(@RequestParam("itemDtlId") int itemDtlId,
                                Model model) {

        System.out.println("ItemDTLController detailItemDTL Start");

        ItemDTL itemDTL = itemDTLService.detailItemDTL(itemDtlId);
        
        // itemDTL의 detailImg를 List로 변환
        String[] detailImgArray = itemDTL.getDetailImg().split(",");
        List<String> detailImgList = Arrays.asList(detailImgArray);
        

        model.addAttribute("itemDTL", itemDTL);
        model.addAttribute("detailImgList",detailImgList);

        return "itemDTL/detailItemDTL";
    }




}
