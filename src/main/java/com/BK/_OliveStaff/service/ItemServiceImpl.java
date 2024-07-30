package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.ItemDao;
import com.BK._OliveStaff.dto.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public List<Item> getItem(int sectionId) {

        System.out.println("ItemServiceImpl getItem Start");

        List<Item> getItem = null;

        try {
            getItem = itemDao.getItem(sectionId);
            System.out.println("getItem.size() = " + getItem.size());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return getItem;
    }

    @Override
    public int insertItem(Item item) {

        System.out.println("ItemServiceImpl insertItem Start");
        int insertCount = 0;

        insertCount = itemDao.insertItem(item);

        return insertCount;
    }
}
