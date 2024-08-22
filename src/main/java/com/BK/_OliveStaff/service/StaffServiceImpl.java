package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.StaffDao;
import com.BK._OliveStaff.dto.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffDao staffDao;

    @Override
    public int totalStaff() {

        log.info("StaffServiceImpl totalStaff Start");

        int totalStaffCnt = staffDao.totalStaff();
        log.info("totStaffCnt = {}", totalStaffCnt);

        return totalStaffCnt;
    }

    @Override
    public List<Staff> getIdNameStaff() {

        log.info("StaffServiceImpl getIdNameStaff Start");
        List<Staff> getIdNameStaff = null;
        
        getIdNameStaff = staffDao.getIdNameStaff();
        log.info("getIdNameStaff.size() = {}", getIdNameStaff.size());

        return getIdNameStaff;
    }

    @Override
    public Staff selectStaffByLogin(Staff staffIdPw) {

        log.info("StaffServiceImpl selectStaffByLogin Start");

        Staff staff = staffDao.selectStaffByLogin(staffIdPw);

        return staff;
    }
}
