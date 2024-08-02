package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dto.Staff;

import java.util.List;

public interface StaffService {
        int totalStaff();

        List<Staff> getIdNameStaff();

        Staff selectStaffByLogin(Staff staffIdPw);
}
