package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Item;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {

    private final SqlSession session;

    @Override
    public List<Item> getItem(int sectionId) {

        System.out.println("ItemDaoImpl getItem Start");
        
        List<Item> getItem = null;
        
        try {
            getItem = session.selectList("getItem", sectionId);
            System.out.println("getItem.size() = " + getItem.size());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return getItem;
    }

    @Override
    public int insertItem(Item item) {

        System.out.println("ItemDaoImpl insertItem Start");
        int insertCount = 0;

        try {
            insertCount = session.insert("itemInsert",item);
            System.out.println("insertCount = " + insertCount);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        return insertCount;
    }
}
