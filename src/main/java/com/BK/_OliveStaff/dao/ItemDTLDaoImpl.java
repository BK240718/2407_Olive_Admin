package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.ItemDTL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemDTLDaoImpl implements ItemDTLDao{

    private final SqlSession session;

    @Override
    public List<ItemDTL> listItemDTL(ItemDTL itemDTL) {

        log.info("ItemDTLDaoImpl listItemDTL Start");
        List<ItemDTL> itemDTLList = null;

        try {
            itemDTLList = session.selectList("itemDTLListAll", itemDTL);
            
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl listItemDTL e.getMessage() = {}", e.getMessage());
        }
        
        return itemDTLList;
    }


    @Override
    public int totalItemDTL() {

        log.info("ItemDTLDaoImpl totalItemDTL Start");
        int totalItemDTLCnt = 0;

        try {
            totalItemDTLCnt = session.selectOne("itemDTLTotal");
            System.out.println("totalItemDTLCnt = " + totalItemDTLCnt);
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl totalItemDTL e.getMessage() = {}", e.getMessage());
        }

        return totalItemDTLCnt;
    }


    @Override
    public ItemDTL detailItemDTL(int itemDtlId) {

        log.info("ItemDTLDaoImpl detailItemDTL Start");
        ItemDTL itemDTL = new ItemDTL();

        try {
            itemDTL = session.selectOne("itemDTLSelectOne", itemDtlId);
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl detailItemDTL e.getMessage() = {}", e.getMessage());
        }
        return itemDTL;
    }

    @Override
    public int insertItemDTL(ItemDTL itemDTL) {

        log.info("ItemDTLDaoImpl insertItemDTL Start");
        int insertResult = 0;

        try {
            insertResult = session.insert("insertItemDTL",itemDTL);
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl insertItemDTL e.getMessage() = {}", e.getMessage());
            e.printStackTrace();
        }

        return insertResult;
    }

    @Override
    public int updateItemDTL(ItemDTL itemDTL) {

        log.info("ItemDTLDaoImpl updateItemDTL Start");
        int updateResult = 0;

        try {
            updateResult = session.update("updateItemDTL", itemDTL);
            log.info("ItemDTLDaoImpl updateItemDTL updateResult = {}", updateResult);
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl updateItemDTL e.getMessage() = {}", e.getMessage());
        }

        return updateResult;
    }

    @Override
    public int deleteItemDTL(int itemDtlId) {

        log.info("ItemDTLDaoImpl deleteItemDTL Start");
        int deleteResult = 0;
        
        try {
            deleteResult = session.delete("deleteItemDTL",itemDtlId);
        } catch (Exception e) {
            log.info("ItemDTLDaoImpl deleteItemDTL e.getMessage() = {}", e.getMessage());
        }
        return deleteResult;
    }


}
