package com.BK._OliveStaff.service;

import com.BK._OliveStaff.dto.Section;

import java.util.List;

public interface SectionService {

    int           totalSection();
    List<Section> listSection(Section section);

    Section detailSection(int sectionId);

    int updateSection(Section section);

    int insertSection(Section section);

    List<Section> mainSection();

    int deleteSection(int sectionId);
}
