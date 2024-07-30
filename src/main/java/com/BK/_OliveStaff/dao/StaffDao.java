package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Staff;

import java.util.List;

public interface StaffDao {

    int totalStaff();

    List<Staff> getIdNameStaff();
}
