package com.BK._OliveStaff.controller;

import com.BK._OliveStaff.dto.Item;
import com.BK._OliveStaff.dto.ItemDTL;
import com.BK._OliveStaff.dto.Section;
import com.BK._OliveStaff.dto.Staff;
import com.BK._OliveStaff.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ItemDTLService itemDTLService;
    private final SectionService sectionService;
    private final StaffService staffService;
    private final ItemService itemService;
    private final ImgUploadService imgUploadService;

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
        /* String[] detailImgArray = itemDTL.getDetailImg().split(",");
        List<String> detailImgList = Arrays.asList(detailImgArray); */

        // JSON 문자열을 List로 변환
        List<String> detailImgList = new ArrayList<>();
        String detailImgJson = itemDTL.getDetailImg();

        // ObjectMapper 클래스:    JSON 문자열 -> Java 객체로
        // TypeReference:         JSON 문자열 -> List<String>으로
        try {
            ObjectMapper mapper = new ObjectMapper();
            detailImgList = mapper.readValue(detailImgJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("itemDTL", itemDTL);
        model.addAttribute("detailImgList",detailImgList);

        return "itemDTL/detailItemDTL";
    }


    @RequestMapping(value = "updateFormItemDTL")
    public String updateFormItemDTL(@RequestParam("itemDtlId") int itemDtlId,
                                    Model model) {

        System.out.println("ItemDTLController detailItemDTL Start");
        Section section  = null;
        Staff staff = null;

        List<Section> getSection = sectionService.getSection();
        List<Staff> getIdNameStaff = staffService.getIdNameStaff();
        List<Item> getItem = itemService.getItem(itemDtlId);

        ItemDTL itemDTL = itemDTLService.detailItemDTL(itemDtlId);

        // JSON 문자열을 List로 변환
        List<String> detailImgList = new ArrayList<>();
        String detailImgJson = itemDTL.getDetailImg();

        // ObjectMapper 클래스:    JSON 문자열 -> Java 객체로
        // TypeReference:         JSON 문자열 -> List<String>으로
        try {
            ObjectMapper mapper = new ObjectMapper();
            detailImgList = mapper.readValue(detailImgJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("itemDTL",itemDTL);
        model.addAttribute("getSection", getSection);
        model.addAttribute("getIdNameStaff", getIdNameStaff);
        model.addAttribute("getItem", getItem);
        model.addAttribute("detailImgList",detailImgList);

        return "itemDTL/updateFormItemDTL";
    }


    @PostMapping(value = "updateItemDTL")
    public String updateItemDTL(@ModelAttribute ItemDTL itemDTL,
                                @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
                                @RequestParam(value = "detailImgFile", required = false) MultipartFile[] detailImgsFile,
                                @RequestParam(value = "colorImgFile", required = false) MultipartFile colorImgFile,
                                Model model) {

        System.out.println("ItemDTLController updateItemDTL Start");
        System.out.println("itemDTL.getItemDtlId() = " + itemDTL.getItemDtlId());
        System.out.println("itemDTL.getThumbnail() = " + itemDTL.getThumbnail());

        // 1-1. 파일 Update 유무 확인
        // 1-2. 있으면 S3 업로드 하고 or 없으면 기존 url 정보 가져와서
        // 1-3. Url 객체 생성
        String thumbnailUrl;
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            thumbnailUrl = imgUploadService.upload(thumbnailFile);
        } else {
            thumbnailUrl = itemDTL.getThumbnail();
        }

        List<String> detailImgUrls = new ArrayList<>();
        if (detailImgsFile != null && detailImgsFile.length > 0) {
            for (MultipartFile detailImg : detailImgsFile) {
                detailImgUrls.add(imgUploadService.upload(detailImg));
            }
        } else {
            detailImgUrls.addAll(parseDetailImgUrls(itemDTL.getDetailImg()));
        }

        String colorImgUrl;
        if (colorImgFile != null && !colorImgFile.isEmpty()) {
            colorImgUrl = imgUploadService.upload(colorImgFile);
        } else {
            colorImgUrl = itemDTL.getColorImg();
        }

        // 2. detailImgUrls 리스트를 JSON 배열 형식으로 변환
        String detailImgUrlsJson;
        try {
            ObjectMapper mapper = new ObjectMapper();
            detailImgUrlsJson = mapper.writeValueAsString(detailImgUrls);
            System.out.println("detailImgUrlsJson = " + detailImgUrlsJson);
        } catch (Exception e) {
            e.printStackTrace();
            detailImgUrlsJson = "[]";
        }

        // 3. 업로드 된 이미지 URL 을 객체 ItemDTL에 저장
        itemDTL.setThumbnail(thumbnailUrl);
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

    private List<String> parseDetailImgUrls(String detailImgJson) {
        List<String> detailImgUrls = new ArrayList<>();
        if(detailImgJson != null && !detailImgJson.isEmpty()) {
            try {
                // ObjectMapper 클래스:    JSON 문자열 -> Java 객체로
                // TypeReference:         JSON 문자열 -> List<String>으로
                ObjectMapper mapper = new ObjectMapper();
                detailImgUrls = mapper.readValue(detailImgJson, new TypeReference<List<String>>() {});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detailImgUrls;
    }



    @RequestMapping(value = "writeFormItemDTL")
    public String writeFormItemDTL(Model model) {

        System.out.println("ItemDTLController writeFormItemDTL Start");
        Section section  = null;
        Staff staff = null;

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
                               @RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                               @RequestParam("detailImgFile") MultipartFile[] detailImgsFile,
                               @RequestParam("colorImgFile") MultipartFile colorImgFile,
                               Model model) {

        System.out.println("ItemDTLController writeItemDTL Start");

        // 1. 이미지 업로드
        String thumbnailUrl = imgUploadService.upload(thumbnailFile);
        List<String> detailImgUrls = new ArrayList<>();
        for (MultipartFile detailImg : detailImgsFile) {
            detailImgUrls.add(imgUploadService.upload(detailImg));
        }
        String colorImgUrl = imgUploadService.upload(colorImgFile);

        // 2. detailImgUrls 리스트를 JSON 배열 형식으로 변환
        String detailImgUrlsJson;
        try {
            ObjectMapper mapper = new ObjectMapper();
            detailImgUrlsJson = mapper.writeValueAsString(detailImgUrls);
            System.out.println("detailImgUrlsJson = " + detailImgUrlsJson);
        } catch (Exception e) {
            e.printStackTrace();
            detailImgUrlsJson = "[]";
        }

        // 3. 업로드 된 이미지 URL 을 객체 ItemDTL에 저장
        itemDTL.setThumbnail(thumbnailUrl);
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

        return "redirect:listItemDTL";
    }

}
