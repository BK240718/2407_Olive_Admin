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

    // 추가 필드
    private String staffName;  // sf.name
    private String secName; // st.secName
    private String itemName; // i.itemName

    // 조회용
    private String search;      private String keyword;
    private String pageNum;
    private int start;          private int end;

    // 기초기말 변환 메서드 추가
    public String getInitialFinalType() {
        return initialFinal == 0 ? "기초" : (initialFinal == 1 ? "기말" : "알수없음");
    }
}
