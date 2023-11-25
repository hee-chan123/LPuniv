package com.project.lpuniv.junhyuk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardDao {

    String getBoardName(@Param("board_no") int board_no);
    Integer getBoardNumberByName(@Param("board_name") String board_name);

}
