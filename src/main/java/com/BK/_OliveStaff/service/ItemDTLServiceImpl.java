package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.ItemDTLDao;
import com.BK._OliveStaff.dto.ItemDTL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDTLServiceImpl implements ItemDTLService {

    private final ItemDTLDao itemDTLDao;
    private final ImgUploadService imgUploadService;


    @Override
    public List<ItemDTL> listItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLServiceImpl listItemDTL Start");

        List<ItemDTL> itemDTLList = itemDTLDao.listItemDTL(itemDTL);
        System.out.println("itemDTLList.size() = " + itemDTLList.size());

        return itemDTLList;
    }


    @Override
    public int totalItemDTL() {

        System.out.println("ItemDTLServiceImpl totalItemDTL Start");
        int totalItemDTLCnt = itemDTLDao.totalItemDTL();

        System.out.println("totalItemDTLCnt = " + totalItemDTLCnt);

        return totalItemDTLCnt;
    }


    @Override
    public ItemDTL detailItemDTL(int itemDtlId) {

        System.out.println("ItemDTLServiceImpl detailItemDTL Start");
        ItemDTL itemDTL = itemDTLDao.detailItemDTL(itemDtlId);

        return itemDTL;
    }

    @Override
    public int insertItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLServiceImpl insertItemDTL Start");
        int insertResult = 0;

        insertResult = itemDTLDao.insertItemDTL(itemDTL);

        return insertResult;
    }

    @Override
    public int updateItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLServiceImpl updateItemDTL Start");
        int updateResult = 0;

        updateResult = itemDTLDao.updateItemDTL(itemDTL);

        return updateResult;
    }

    @Override
    public int deleteItemDTL(int itemDtlId) {

        System.out.println("ItemDTLServiceImpl deleteItemDTL Start");

        // 1. 이미지 url 가져오기
        ItemDTL itemDTL = itemDTLDao.detailItemDTL(itemDtlId);
        
        if (itemDTL == null) {
            throw new RuntimeException("Item detail not found for ID: "+itemDtlId);
        }

        System.out.println("itemDTL.getColorImg() = " + itemDTL.getColorImg());

        // 2. 이미지 url 삭제
        boolean deleteColorImg  =   imgUploadService.deteleImg(itemDTL.getColorImg());
        boolean deleteThumbnail =   deleteImgFromJson(itemDTL.getThumbnail());
        boolean deleteDetailImg =   deleteImgFromJson(itemDTL.getDetailImg());

        System.out.println("deleteColorImg = " + deleteColorImg);
        System.out.println("deleteThumbnail = " + deleteThumbnail);
        System.out.println("deleteDetailImg = " + deleteDetailImg);


        // 3. DB에서 제품 상세 정보 삭제
        int deleteResult = 0;
        deleteResult = itemDTLDao.deleteItemDTL(itemDtlId);

        return deleteResult;
    }

    // JSON 문자열을 List<String>으로 변환하는 메소드
    @Override
    public List<String> convertJsonToList(String jsonString) {

        System.out.println("ItemDTLServiceImpl convertJsonToList Start");

        List<String> list = new ArrayList<>();
        // ObjectMapper 클래스:    JSON 문자열 <-> Java 객체 변환
        ObjectMapper mapper = new ObjectMapper();

        try {
            // readValue(String content, TypeReference<T> valueTypeRef):
            // JSON 문자열 -> valueTypeRef(구체적인 타입 ex: List<String>으로)
            list = mapper.readValue(jsonString, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // List<String>을 JSON 문자열로 변환하는 메소드
    @Override
    public String convertListToJson(List<String> list) {

        System.out.println("ItemDTLServiceImpl convertListToJson Start");
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;
        
        try {
            // writeValueAsString(Object value)
            // Java 객체를 JSON 문자열로 변환
            jsonString = mapper.writeValueAsString(list);
            System.out.println("jsonString = " + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
            jsonString = "[]";
        }

        return jsonString;
    }


    // 1. 파일 Update 유무 확인
    // 2. 있으면 S3 업로드 하고 or 없으면 기존 url 정보 가져와서
    // 3. Url 객체로 return
    @Override
    public String getFileUrl(MultipartFile file, String existingUrl) {

        System.out.println("ItemDTLServiceImpl getFileUrl Start");

        if(file != null && !file.isEmpty()) {
            return imgUploadService.upload(file);
        } else {
            return existingUrl;
        }
    }

    @Override
    public List<String> getImageUrlsFromJson(MultipartFile[] imgFiles, String existingJson) {

        System.out.println("ItemDTLServiceImpl getDetailImgUrls Start");

        List<String> ImgUrls = new ArrayList<>();

        if (imgFiles != null && imgFiles.length > 0) {
            for (MultipartFile detailImg : imgFiles) {
                ImgUrls.add(imgUploadService.upload(detailImg));
            }
        } else {
            ImgUrls.addAll(convertJsonToList(existingJson));
        }
        return ImgUrls;
    }

    private boolean deleteImgFromJson(String jsonUrls) {
        if (jsonUrls != null && !jsonUrls.isEmpty()) {
            try {
                List<String> urls = new ObjectMapper().readValue(jsonUrls, new TypeReference<List<String>>() {});
                boolean allDeleted = true;
                for (String url : urls) {
                    System.out.println("url = " + url);
                    boolean deleted = imgUploadService.deteleImg(url);
                    if (!deleted) {
                        allDeleted = false;
                    }
                }
                return  allDeleted;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
