package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.StaffDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffDao staffDao;

    @Override
    public int totalStaff() {

        System.out.println("StaffServiceImpl Start");
        int totalStaffCnt = staffDao.totalStaff();
        System.out.println("totStaffCnt = " + totalStaffCnt);

        return totalStaffCnt;
    }
}
