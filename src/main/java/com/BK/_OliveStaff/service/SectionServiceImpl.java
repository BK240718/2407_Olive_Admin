package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dao.SectionDao;
import com.BK._OliveStaff.dto.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionDao sectionDao;

    @Override
    public int totalSection() {

        System.out.println("SectionServiceImpl totalSection Start");
        int totalStaffCnt = sectionDao.totalSection();

        System.out.println("totalStaffCnt = " + totalStaffCnt);
        return totalStaffCnt;
    }

    @Override
    public List<Section> listSection(Section section) {

        System.out.println("SectionServiceImpl listSection Start");

        List<Section> sectionList = null;
        sectionList = sectionDao.listSection(section);
        System.out.println("listSection.size()"+sectionList.size());

        return sectionList;
    }

    @Override
    public Section detailSection(int sectionId) {

        System.out.println("SectionServiceImpl detailSection Start");

        Section section = null;
        section = sectionDao.detailSection(sectionId);

        return section;
    }

    @Override
    public int updateSection(Section section) {

        System.out.println("SectionServiceImpl updateCount Start");
        System.out.println("SectionServiceImpl section.getSectionId() = " + section.getSectionId());
        int updateCount = 0;

        updateCount = sectionDao.updateSection(section);

        return updateCount;
    }

    @Override
    public int insertSection(Section section) {

        System.out.println("SectionServiceImpl updateCount Start");
        int insertCount = 0;

        insertCount = sectionDao.insertSection(section);

        return insertCount;
    }


}
