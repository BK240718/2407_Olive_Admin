package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dto.ItemDTL;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemDTLService {

    List<ItemDTL> listItemDTL(ItemDTL itemDTL);

    int totalItemDTL();

    ItemDTL detailItemDTL(int itemDtlId);

    int insertItemDTL(ItemDTL itemDTL);

    int updateItemDTL(ItemDTL itemDTL);

    int deleteItemDTL(int itemDtlId);

    List<String> convertJsonToList(String jsonString);

    String convertListToJson(List<String> list);

    String getFileUrl(MultipartFile file, String existingUrl);

    List<String> getDetailImgUrls(MultipartFile[] detailImgs, String existingJson);


}
