package com.project.lpuniv.Juchan.OCC.Dao;

import com.project.lpuniv.Dto;
import com.project.lpuniv.Juchan.OCC.Dto.OccDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OccDao {

    void occInsert(OccDto occDto);
}
