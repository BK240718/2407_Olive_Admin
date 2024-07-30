package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Section;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SectionDaoImpl implements SectionDao {

    private final SqlSession session;

    @Override
    public int totalSection() {

        int totalSectionCnt = 0;
        System.out.println("SectionDaoImpl totalSection Start");

        try {
            totalSectionCnt = session.selectOne("sectionTotal");
            System.out.println("totalSectionCnt = " + totalSectionCnt);
        } catch (Exception e) {
            System.out.println("SectionDaoImpl totalSection Exception: "+e.getMessage());

        }
        return totalSectionCnt;
    }

    @Override
    public List<Section> listSection(Section section) {

        System.out.println("SectionDaoImpl listSection Start");
        List<Section> sectionList = null;

        try {
            //                                          Map ID          parameter
            sectionList = session.selectList("sectionListAll", section);
            System.out.println("sectionList.size() = "+sectionList.size());
        } catch (Exception e) {
            System.out.println("SectionDaoImpl listSection Exception: "+e.getMessage());

        }

        return sectionList;
    }

    @Override
    public Section detailSection(int sectionId) {

        System.out.println("SectionDaoImpl detailSection Start");
        Section section = new Section();

        try {
            section = session.selectOne("sectionSelectOne", sectionId);
            System.out.println("SectionDaoImpl section.getSecName() = " +section.getSecName() );
        } catch (Exception e) {
            System.out.println("SectionDaoImpl detailSection Exception: "+e.getMessage());

        }
        return section;
    }

    @Override
    public int updateSection(Section section) {

        System.out.println("SectionDaoImpl updateSection Start");
        System.out.println("SectionDaoImpl section.getSectionId() = " + section.getSectionId());
        int updateCount = 0;

        try {
            updateCount = session.update("sectionUpdate", section);
        } catch (Exception e) {
            System.out.println("SectionDaoImpl updateSection Exception: "+e.getMessage());
        }
        return updateCount;
    }

    @Override
    public int insertSection(Section section) {

        System.out.println("SectionDaoImpl insertSection Start");
        int insertCount = 0;

        try {
            insertCount = session.insert("sectionWrite", section);
        } catch (Exception e) {
            System.out.println("SectionDaoImpl insertSection Exception: "+e.getMessage());
        }

        return insertCount;
    }

    @Override
    public List<Section> mainSection() {
        System.out.println("SectionDaoImpl mainSection Start");
        List<Section> sectionMain = null;

        try {
            sectionMain = session.selectList("mainSection");
            System.out.println("SectionDaoImpl mainSection Exception: "+sectionMain.size());
        } catch (Exception e) {
            System.out.println("SectionDaoImpl mainSection Exception: "+e.getMessage());
        }
        return sectionMain;
    }

    @Override
    public int deleteSection(int sectionId) {

        System.out.println("SectionDaoImpl deleteSection Start");

        int deleteCount = 0;

        try {
            deleteCount = session.delete("deleteSection",sectionId);

        } catch (Exception e) {
            System.out.println("SectionDaoImpl deleteSection e.getMessage() = " + e.getMessage());
        }
        return deleteCount;
    }

    @Override
    public List<Section> getSection() {

        System.out.println("SectionDaoImpl getSection Start");

        List<Section> getSection = null;

        try {
            getSection = session.selectList("getSection");
            System.out.println("getSection.size() = " + getSection.size());
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        return getSection;
    }


}
