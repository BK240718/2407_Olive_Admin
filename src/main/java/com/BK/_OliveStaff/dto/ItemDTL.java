package com.BK._OliveStaff.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ItemDTL {
    private int itemDtlId;
    private String yearMonth;
    private int initialFinal;
    private int itemId;
    private int staffId;
    private String colorName;
    private int purchasePrice;
    private int salesPrice;
    private int quantity;
    private String regDate;
    private int status;
    private String thumbnail;
    private String detailImg;
    private String colorImg;

    // 추가 필드
    private String staffName;   // sf.name
    private String secName;     // st.secName
    private String itemName;    // i.itemName
    private int sectionId;      // st.sectionId

    // 조회용
    private String search;      private String keyword;
    private String pageNum;
    private int start;          private int end;

    // 기초기말 변환 메서드 추가
    public String getInitialFinalType() {
        return initialFinal == 0 ? "기초" : (initialFinal == 1 ? "기말" : "알수없음");
    }
    
    // String regDate 문자열을 Date 객체로 변환
    public Date getRegDateAsDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(regDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
