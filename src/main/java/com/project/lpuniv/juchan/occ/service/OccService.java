package com.project.lpuniv.juchan.occ.service;

import com.project.lpuniv.juchan.occ.dao.OccDao;
import com.project.lpuniv.juchan.occ.dto.OccDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccService {

    @Autowired
    private OccDao occDao;

    public void occInsert(OccDto occDto){
        occDao.occInsert(occDto);
    }

    public Integer occAllCount(){
        return occDao.occAllCount();
    }

    public List<OccDto> occAllSelectDesc(){
       return occDao.occAllSelectDesc();
    }

    public List<OccDto> occAllSelectDescPage(int startRow, int size) {
        return occDao.occAllSelectDescPage(startRow,size);
    };

    public OccDto occOneSelect(int occ_NO){
        return occDao.occOneSelect(occ_NO);
    }

    public void occModify(OccDto occDto) {
        occDao.occModify(occDto);
    }


}
