package com.project.lpuniv.Juchan.OCC.Controller;

import com.project.lpuniv.Juchan.OCC.Dao.OccDao;
import com.project.lpuniv.Juchan.OCC.Dto.OccDto;
import com.project.lpuniv.Juchan.OCC.Service.OccService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@Slf4j
public class OccController {

    @Autowired
    private OccService occService;

    @GetMapping("occ/occ_insert")
    public String OccInsert() {
        return "/juchan/Occ/occ_insert";
    }
    @PostMapping("occ/occ_insert")
    public String OccInsertP(OccDto occDto) {
        occService.occInsert(occDto);
        return "/dd/test";
    }


}
