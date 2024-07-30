package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.ItemDTLDao;
import com.BK._OliveStaff.dto.ItemDTL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDTLServiceImpl implements ItemDTLService {

    private final ItemDTLDao itemDTLDao;


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


}
