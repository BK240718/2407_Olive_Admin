package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Item;
import com.BK._OliveStaff.dto.ItemDTL;
import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.dto.Staff;
import com.BK._OliveStaff.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemDTLController {

    private final ItemDTLService    itemDTLService;
    private final SectionService    sectionService;
    private final StaffService      staffService;
    private final ItemService       itemService;
    private final ImgUploadService  imgUploadService;


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

        // 3. Select List
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

        // JSON 문자열을 List로 변환
        String thumbnailJson = itemDTL.getThumbnail();
        List<String> thumbnailList = itemDTLService.convertJsonToList(thumbnailJson);
        String detailImgJson = itemDTL.getDetailImg();
        List<String> detailImgList = itemDTLService.convertJsonToList(detailImgJson);

        model.addAttribute("itemDTL", itemDTL);
        model.addAttribute("thumbnailList",thumbnailList);
        model.addAttribute("detailImgList",detailImgList);

        return "itemDTL/detailItemDTL";
    }


    @RequestMapping(value = "updateFormItemDTL")
    public String updateFormItemDTL(@RequestParam("itemDtlId") int itemDtlId,
                                    Model model) {

        System.out.println("ItemDTLController detailItemDTL Start");

        List<Section> getSection = sectionService.getSection();
        List<Staff> getIdNameStaff = staffService.getIdNameStaff();
        List<Item> getItem = itemService.getItem(itemDtlId);

        ItemDTL itemDTL = itemDTLService.detailItemDTL(itemDtlId);


        // JSON 문자열을 List로 변환
        String thumbnailImgJson = itemDTL.getThumbnail();
        String detailImgJson = itemDTL.getDetailImg();

        List<String> thumbnailImgList = itemDTLService.convertJsonToList(thumbnailImgJson);
        List<String> detailImgList = itemDTLService.convertJsonToList(detailImgJson);


        model.addAttribute("itemDTL",itemDTL);
        model.addAttribute("getSection", getSection);
        model.addAttribute("getIdNameStaff", getIdNameStaff);
        model.addAttribute("getItem", getItem);
        model.addAttribute("thumbnailImgList",thumbnailImgList);
        model.addAttribute("detailImgList",detailImgList);

        return "itemDTL/updateFormItemDTL";
    }


    @PostMapping(value = "updateItemDTL")
    public String updateItemDTL(@ModelAttribute ItemDTL itemDTL,
                                @RequestParam(value = "colorImgFile", required = false) MultipartFile colorImgFile,
                                @RequestParam(value = "thumbnailFile", required = false) MultipartFile[] thumbnailsFile,
                                @RequestParam(value = "detailImgFile", required = false) MultipartFile[] detailImgsFile,
                                Model model) {

        System.out.println("ItemDTLController updateItemDTL Start");
        System.out.println("itemDTL.getItemDtlId() = " + itemDTL.getItemDtlId());
        System.out.println("itemDTL.getThumbnail() = " + itemDTL.getThumbnail());

        // 1. 파일 업로드 및 URL 생성
        String colorImgUrl = itemDTLService.getFileUrl(colorImgFile, itemDTL.getColorImg());
        List<String> thumbnailUrls = itemDTLService.getImageUrlsFromJson(thumbnailsFile, itemDTL.getThumbnail());
        List<String> detailImgUrls = itemDTLService.getImageUrlsFromJson(detailImgsFile, itemDTL.getDetailImg());

        // 2. thumbnailUrls, detailImgUrls 리스트를 JSON 배열 형식으로 변환
        String thumbnailImgUrlsJson = itemDTLService.convertListToJson(thumbnailUrls);
        String detailImgUrlsJson = itemDTLService.convertListToJson(detailImgUrls);

        // 3. 업로드 된 이미지 URL 을 객체 ItemDTL에 저장
        itemDTL.setThumbnail(thumbnailImgUrlsJson);    // JSON 배열 형식으로 저장
        itemDTL.setDetailImg(detailImgUrlsJson);    // JSON 배열 형식으로 저장
        itemDTL.setColorImg(colorImgUrl);

        System.out.println("itemDTL.getThumbnail() = " + itemDTL.getThumbnail());
        System.out.println("itemDTL.getDetailImg() = " + itemDTL.getDetailImg());
        System.out.println("itemDTL.getColorImg() = " + itemDTL.getColorImg());
        System.out.println("itemDTL.getItemDtlId() = " + itemDTL.getItemDtlId());

        model.addAttribute("itemDtlId",itemDTL.getItemDtlId());

        // 4. ItemDTL Update 작업
        int updateResult = itemDTLService.updateItemDTL(itemDTL);

        if (updateResult > 0) return "redirect:listItemDTL";
        else {
            model.addAttribute("msg","입력 실패");
            return "forward:updateFormItemDTL";
        }

    }


    @RequestMapping(value = "writeFormItemDTL")
    public String writeFormItemDTL(Model model) {

        System.out.println("ItemDTLController writeFormItemDTL Start");

        List<Section> getSection = sectionService.getSection();
        List<Staff> getIdNameStaff = staffService.getIdNameStaff();

        model.addAttribute("getSection", getSection);
        model.addAttribute("getIdNameStaff", getIdNameStaff);

        return "itemDTL/writeFormItemDTL";
    }


    @ResponseBody
    @RequestMapping(value = "getItemAjax", method = RequestMethod.GET)
    public Map<String, Object> getItem(@RequestParam("sectionId") int sectionId) {

        System.out.println("ItemDTLController getItem Start");

        Map<String, Object> reseponse = new HashMap<String, Object>();
        List<Item> items = itemService.getItem(sectionId);

        reseponse.put("status", "200 OK");
        reseponse.put("data", Map.of("items", items));

        return reseponse;
    }

    @PostMapping(value = "writeItemDTL")
    public String writeItemDTL(@ModelAttribute ItemDTL itemDTL,
                               @RequestParam("thumbnailFile") MultipartFile[] thumbnailsFile,
                               @RequestParam("detailImgFile") MultipartFile[] detailImgsFile,
                               @RequestParam("colorImgFile") MultipartFile colorImgFile,
                               Model model) {

        System.out.println("ItemDTLController writeItemDTL Start");

        // 1. 이미지 업로드
        String colorImgUrl = imgUploadService.upload(colorImgFile);
        List<String> thumbnailUrls = new ArrayList<>();
        for (MultipartFile thumbnail : thumbnailsFile) {
            thumbnailUrls.add(imgUploadService.upload(thumbnail));
        }
        List<String> detailImgUrls = new ArrayList<>();
        for (MultipartFile detailImg : detailImgsFile) {
            detailImgUrls.add(imgUploadService.upload(detailImg));
        }

        // 2. thumbnailUrls, detailImgUrls 리스트를 JSON 배열 형식으로 변환
        String thumbnailUrlsJson = itemDTLService.convertListToJson(thumbnailUrls);
        String detailImgUrlsJson = itemDTLService.convertListToJson(detailImgUrls);

        // 3. 업로드 된 이미지 URL 을 객체 ItemDTL에 저장
        itemDTL.setThumbnail(thumbnailUrlsJson);    // JSON 배열 형식으로 저장
        itemDTL.setDetailImg(detailImgUrlsJson);    // JSON 배열 형식으로 저장
        itemDTL.setColorImg(colorImgUrl);

        System.out.println("itemDTL.getThumbnail() = " + itemDTL.getThumbnail());
        System.out.println("itemDTL.getDetailImg() = " + itemDTL.getDetailImg());
        System.out.println("itemDTL.getColorImg() = " + itemDTL.getColorImg());

        // 4. ItemDTL Insert 작업
        int insertResult = itemDTLService.insertItemDTL(itemDTL);
        if (insertResult > 0) return "redirect:listItemDTL";
        else {
            model.addAttribute("msg","입력 실패");
            return "forward:writeFormItemDTL";
        }

    }


    
    @RequestMapping(value = "deleteItemDTL")
    public String deleteItemDTL(@RequestParam("itemDtlId") int itemDtlId,
                                Model model) {
        System.out.println("ItemDTLController deleteItemDTL Start");

        int deleteResult = itemDTLService.deleteItemDTL(itemDtlId);
        System.out.println("deleteResult = " + deleteResult);

        return "redirect:listItemDTL";
    }

}
