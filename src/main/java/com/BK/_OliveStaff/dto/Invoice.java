package com.BK._OliveStaff.dto;

import lombok.Data;

@Data
public class Invoice {
    private int invoiceId;
    private int customerId;
    private String orderDate;
    private int totalPrice;
    private int status;
    private String request;
    private String address1;
    private String address2;
    private String receiver;
    private String phoneNum;

    // 추가 필드
    private String customerName;  // c.customerName

    // 조회용
    private String search;      private String keyword;
    private String pageNum;
    private int start;          private int end;
}
