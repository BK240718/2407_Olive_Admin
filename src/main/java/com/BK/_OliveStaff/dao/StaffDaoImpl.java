package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StaffDaoImpl implements StaffDao{


    // MyBatis DB 연동
    private final SqlSession session;


    @Override
    public int totalStaff() {

        log.info("StaffDaoImpl totalStaff Start");
        int totalStaffCnt = 0;

        try {

            totalStaffCnt = session.selectOne("staffTotal");
            log.info("totStaffCnt = {}", totalStaffCnt);
        } catch (Exception e) {
            System.out.println();
            log.info("StaffDaoImpl Exception = {}", e.getMessage());
        }

        return totalStaffCnt;
    }


    @Override
    public List<Staff> getIdNameStaff() {

        log.info("StaffDaoImpl getIdNameStaff Start");
        List<Staff> getIdNameStaff = null;
        
        try {
            getIdNameStaff = session.selectList("getIdNameStaff");
            log.info("getIdNameStaff.size() = {}", getIdNameStaff.size());
        } catch (Exception e) {
            log.info("e.getMessage() = {}", e.getMessage());
        }
        
        return getIdNameStaff;
    }

    @Override
    public Staff selectStaffByLogin(Staff staffIdPw) {

        log.info("StaffDaoImpl selectStaffByLogin Start");
        Staff staff = null;
        
        try {
            staff = session.selectOne("selectStaffByLogin", staffIdPw);
            log.info("staff = {}", staff.getStaffId());
        } catch (Exception e) {
            log.info("e.getMessage() = {}", e.getMessage());
        }

        return staff;
    }
}
