package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Item;

import java.util.List;

public interface ItemDao {

    List<Item> getItem(int sectionId);

    int insertItem(Item item);
}
