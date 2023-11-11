package com.project.lpuniv.junhyuk.service;

import com.project.lpuniv.junhyuk.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public String getBoardName(int board_no) {

        return boardDao.getBoardName(board_no);
    }

    public Integer getBoardNumberByName(String board_name){
        return boardDao.getBoardNumberByName(board_name);
    }

}
