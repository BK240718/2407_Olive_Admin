package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.ItemDTL;

import java.util.List;

public interface ItemDTLDao {

    List<ItemDTL> listItemDTL(ItemDTL itemDTL);

    int totalItemDTL();

    ItemDTL detailItemDTL(int itemDtlId);

    int insertItemDTL(ItemDTL itemDTL);

    int updateItemDTL(ItemDTL itemDTL);

    int deleteItemDTL(int itemDtlId);


}
