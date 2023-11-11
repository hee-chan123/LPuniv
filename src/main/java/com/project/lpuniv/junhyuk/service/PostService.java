package com.project.lpuniv.junhyuk.service;

import com.project.lpuniv.junhyuk.dao.PostDao;
import com.project.lpuniv.junhyuk.dto.FileAttachment;
import com.project.lpuniv.junhyuk.dto.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PostService {

    @Autowired
    private PostDao postDao;

    public List<Post> getAllPostsByBoard(int board_no) {
        return postDao.getAllPostsByBoard(board_no);
    }

    public int createPost(Post post) {
        int lastNum = postDao.getLastPostNumByBoard(post.getBoard_no());
        post.setNum(lastNum + 1);
        postDao.createPost(post);
        return post.getPost_no();
    }



    public int getLastPostNumByBoard(int board_no) {

        return postDao.getLastPostNumByBoard(board_no);
    }

    public Post findByPostNo(int post_no) {

        return postDao.findByPostNo(post_no);
    }




}

