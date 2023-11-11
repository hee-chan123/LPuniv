package com.project.lpuniv.minho.listenLec.controller;

import com.project.lpuniv.minho.listenLec.dto.LecInfoDto;
import com.project.lpuniv.minho.listenLec.dto.LecListDto;
import com.project.lpuniv.minho.listenLec.dto.LecVideoDto;
import com.project.lpuniv.minho.listenLec.dto.SchsDto;
import com.project.lpuniv.minho.listenLec.service.LecVideoService;
import com.project.lpuniv.minho.listenLec.service.LectListService;
import com.project.lpuniv.minho.listenLec.service.LecInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/minho")
public class ListenLecController {
    @Autowired
    LecInfoService lecInfoService;
    @Autowired
    LectListService lectListService;
    @Autowired
    LecVideoService lecVideoService;

    @GetMapping("/lecInfo")
    public String getLecInfo(Model model) {
        List<LecInfoDto> listenLecDtos = lecInfoService.selectAllLitenLec();
        model.addAttribute("dto", listenLecDtos);
        return "minho/listenLec/lecInfo";
    }

    @GetMapping("/lecList")
    public String getLecList(Model model, @RequestParam("OCC_NO") int OCC_NO) {
        List<LecListDto> lecListDtos = lectListService.selectLecList(OCC_NO);
        model.addAttribute("lectList", lecListDtos);
        return "minho/listenLec/lecList";
    }

    @GetMapping("/lecVideo")
    public String getLecVideo(Model model, @RequestParam("CCIM_NO") int CCIM_NO,
                              @RequestParam("OCC_NO") int OCC_NO, HttpSession session) {
        int stud_no = 1;
        try {
            LecVideoDto lecVideoDto = lecVideoService.selectLecVideo(CCIM_NO, OCC_NO);
            SchsDto schsDto = lecVideoService.selectSchs(stud_no);
            if (schsDto == null) {
                lecVideoService.insertSchs(schsDto);
            }
            model.addAttribute("lecVideo", lecVideoDto);
            return "minho/listenLec/lecVideo";
        } catch (Exception e) {
            return "redirect:/minho/lecList";
        }
    }
}
