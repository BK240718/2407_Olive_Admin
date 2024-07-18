package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.StaffDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffDao sd;

    @Override
    public int totalStaff() {

        System.out.println("StaffServiceImpl Start");
        int totStaffCnt = sd.totStaff();
        System.out.println("totStaffCnt = " + totStaffCnt);

        return totStaffCnt;
    }
}
