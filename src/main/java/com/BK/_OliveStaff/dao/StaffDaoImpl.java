package com.BK._OliveStaff.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StaffDaoImpl implements StaffDao{

    // MyBatis DB 연동
    private final SqlSession session;

    @Override
    public int totStaff() {

        int totStaffCnt = 0;
        System.out.println("StaffDaoImpl Start");

        try {
            
            totStaffCnt = session.selectOne("staffTotal");
            System.out.println("totStaffCnt = " + totStaffCnt);
        } catch (Exception e) {
            System.out.println("StaffDaoImpl Exception = " + e.getMessage());
        }

        return totStaffCnt;
    }
}
