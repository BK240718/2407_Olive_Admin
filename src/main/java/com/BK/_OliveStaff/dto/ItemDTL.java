package com.BK._OliveStaff.dto;

import lombok.Data;

@Data
public class ItemDTL {
    private int itemDtlId;
    private String yearMonth;
    private int initialFinal;
    private int itemId;
    private int staffId;
    private String colorName;
    private Integer purchasePrice;
    private Integer salesPrice;
    private Integer quantity;
    private String regDate;
    private Integer status;
    private String thumbnail;
    private String detailImg;
    private String colorImg;
}
