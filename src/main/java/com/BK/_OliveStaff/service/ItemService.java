package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dto.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItem(int sectionId);

    int insertItem(Item item);
}
