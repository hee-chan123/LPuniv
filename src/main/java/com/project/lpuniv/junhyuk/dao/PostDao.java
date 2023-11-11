package com.project.lpuniv.junhyuk.dao;

import com.project.lpuniv.junhyuk.dto.FileAttachment;
import com.project.lpuniv.junhyuk.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostDao {


    List<Post> getAllPostsByBoard(int board_no);

    int createPost(Post post);

    Post findByPostNo(@Param("post_no") int post_no);

    int updatePost(Post post);

    int incrementPostHits(@Param("post_no") int post_no);

    int deletePost(@Param("post_no") int post_no);

    int getLastPostNumByBoard(@Param("board_no") int board_no);


}
