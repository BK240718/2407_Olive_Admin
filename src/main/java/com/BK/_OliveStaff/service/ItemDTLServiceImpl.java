package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.ItemDTLDao;
import com.BK._OliveStaff.dto.ItemDTL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDTLServiceImpl implements ItemDTLService {

    private final ItemDTLDao itemDTLDao;
    private final ImgUploadService imgUploadService;


    @Override
    public List<ItemDTL> listItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLServiceImpl listItemDTL Start");

        List<ItemDTL> itemDTLList = null;
        itemDTLList = itemDTLDao.listItemDTL(itemDTL);
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

        ItemDTL itemDTL = null;
        itemDTL = itemDTLDao.detailItemDTL(itemDtlId);

        return itemDTL;
    }

    @Override
    public int insertItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLServiceImpl detailItemDTL Start");
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

        System.out.println("itemDTL.getThumbnail() = " + itemDTL.getThumbnail());
        System.out.println("itemDTL.getColorImg() = " + itemDTL.getColorImg());


        // 2. 이미지 url 삭제
        boolean deleteThumbnail =   imgUploadService.deteleImg(itemDTL.getThumbnail());
        boolean deleteColorImg  =   imgUploadService.deteleImg(itemDTL.getColorImg());
        boolean deleteDetailImg =   deleteImgFromJson(itemDTL.getDetailImg());

        System.out.println("deleteThumbnail = " + deleteThumbnail);
        System.out.println("deleteColorImg = " + deleteColorImg);
        System.out.println("deleteDetailImg = " + deleteDetailImg);


        // 3. DB에서 제품 상세 정보 삭제
        int deleteResult = 0;
        deleteResult = itemDTLDao.deleteItemDTL(itemDtlId);

        return deleteResult;
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
