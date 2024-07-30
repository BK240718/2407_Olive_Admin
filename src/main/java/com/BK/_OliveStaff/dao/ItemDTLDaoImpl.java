package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.ItemDTL;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDTLDaoImpl implements ItemDTLDao{

    private final SqlSession session;

    @Override
    public List<ItemDTL> listItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLDaoImpl listItemDTL Start");
        List<ItemDTL> itemDTLList = null;

        try {
            itemDTLList = session.selectList("itemDTLListAll", itemDTL);
            
        } catch (Exception e) {
            System.out.println("ItemDTLDaoImpl listItemDTL e.getMessage() = " + e.getMessage());
        }
        
        return itemDTLList;
    }


    @Override
    public int totalItemDTL() {

        System.out.println("ItemDTLDaoImpl totalItemDTL Start");
        int totalItemDTLCnt = 0;

        try {
            totalItemDTLCnt = session.selectOne("itemDTLTotal");
            System.out.println("totalItemDTLCnt = " + totalItemDTLCnt);
        } catch (Exception e) {
            System.out.println("ItemDTLDaoImpl totalItemDTL e.getMessage() = " + e.getMessage());
        }

        return totalItemDTLCnt;
    }


    @Override
    public ItemDTL detailItemDTL(int itemDtlId) {

        System.out.println("ItemDTLDaoImpl detailItemDTL Start");
        ItemDTL itemDTL = new ItemDTL();

        try {
            itemDTL = session.selectOne("itemDTLSelectOne", itemDtlId);
        } catch (Exception e) {
            System.out.println("ItemDTLDaoImpl detailItemDTL e.getMessage() = " + e.getMessage());
        }
        return itemDTL;
    }

    @Override
    public int insertItemDTL(ItemDTL itemDTL) {

        System.out.println("ItemDTLDaoImpl insertItemDTL Start");
        int insertResult = 0;

        try {
            insertResult = session.insert("insertItemDTL",itemDTL);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            e.printStackTrace();
        }

        return insertResult;
    }


}
