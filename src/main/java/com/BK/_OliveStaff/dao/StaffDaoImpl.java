package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Staff;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StaffDaoImpl implements StaffDao{


    // MyBatis DB 연동
    private final SqlSession session;


    @Override
    public int totalStaff() {

        System.out.println("StaffDaoImpl totalStaff Start");
        int totalStaffCnt = 0;

        try {

            totalStaffCnt = session.selectOne("staffTotal");
            System.out.println("totStaffCnt = " + totalStaffCnt);
        } catch (Exception e) {
            System.out.println("StaffDaoImpl Exception = " + e.getMessage());
        }

        return totalStaffCnt;
    }


    @Override
    public List<Staff> getIdNameStaff() {

        System.out.println("StaffDaoImpl totalStaff Start");
        List<Staff> getIdNameStaff = null;
        
        try {
            getIdNameStaff = session.selectList("getIdNameStaff");
            System.out.println("getIdNameStaff.size() = " + getIdNameStaff.size());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());    
        }
        
        return getIdNameStaff;
    }
}
