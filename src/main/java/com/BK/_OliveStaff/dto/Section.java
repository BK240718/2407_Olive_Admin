package com.BK._OliveStaff.dto;

import lombok.Data;

@Data
public class Section {
    private int sectionId;
    private String secName;

    // 조회용
    private String search;      private String keyword;
    private String pageNum;
    private int start;          private int end;

}
