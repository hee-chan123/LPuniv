package com.project.lpuniv.Juchan.OCC.Service;

import com.project.lpuniv.Juchan.OCC.Dao.OccDao;
import com.project.lpuniv.Juchan.OCC.Dto.OccDto;
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


}
