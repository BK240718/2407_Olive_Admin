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
    public int totalStaff() {

        int totalStaffCnt = 0;
        System.out.println("StaffDaoImpl Start");

        try {

            totalStaffCnt = session.selectOne("staffTotal");
            System.out.println("totStaffCnt = " + totalStaffCnt);
        } catch (Exception e) {
            System.out.println("StaffDaoImpl Exception = " + e.getMessage());
        }

        return totalStaffCnt;
    }
}
