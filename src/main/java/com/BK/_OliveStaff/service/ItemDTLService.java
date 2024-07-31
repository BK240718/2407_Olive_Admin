package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dto.ItemDTL;

import java.util.List;

public interface ItemDTLService {

    List<ItemDTL> listItemDTL(ItemDTL itemDTL);

    int totalItemDTL();

    ItemDTL detailItemDTL(int itemDtlId);

    int insertItemDTL(ItemDTL itemDTL);

    int updateItemDTL(ItemDTL itemDTL);

    int deleteItemDTL(int itemDtlId);
}
