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
    public List<ItemDTL> listItemDTL() {

        System.out.println("ItemDTLDaoImpl listItemDTL Start");

        List<ItemDTL> itemDTLList = null;

        try {
            itemDTLList = session.selectList("itemDTLListAll");
            
        } catch (Exception e) {
            System.out.println("ItemDTLDaoImpl listItemDTL e.getMessage() = " + e.getMessage());
        }
        
        return itemDTLList;
    }
}
