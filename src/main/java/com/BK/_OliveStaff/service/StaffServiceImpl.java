package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.StaffDao;
import com.BK._OliveStaff.dto.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffDao staffDao;

    @Override
    public int totalStaff() {

        System.out.println("StaffServiceImpl totalStaff Start");
        int totalStaffCnt = staffDao.totalStaff();
        System.out.println("totStaffCnt = " + totalStaffCnt);

        return totalStaffCnt;
    }

    @Override
    public List<Staff> getIdNameStaff() {

        System.out.println("StaffServiceImpl getIdNameStaff Start");
        List<Staff> getIdNameStaff = null;
        
        getIdNameStaff = staffDao.getIdNameStaff();
        System.out.println("getIdNameStaff.size() = " + getIdNameStaff.size());
        return getIdNameStaff;
    }

    @Override
    public Staff selectStaffByLogin(Staff staffIdPw) {

        System.out.println("StaffServiceImpl selectStaffByLogin Start");

        Staff staff = staffDao.selectStaffByLogin(staffIdPw);

        return staff;
    }
}
