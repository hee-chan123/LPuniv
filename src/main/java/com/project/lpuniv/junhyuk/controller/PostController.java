package com.project.lpuniv.junhyuk.controller;


import com.project.lpuniv.junhyuk.dto.FileAttachment;
import com.project.lpuniv.junhyuk.dto.Post;
import com.project.lpuniv.junhyuk.service.BoardService;
import com.project.lpuniv.junhyuk.service.FileService;
import com.project.lpuniv.junhyuk.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/boards")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    FileService fileService;


    @GetMapping("/main")
    public String mainPage() {

        return "main";
    }


    @GetMapping("/{board_name}/posts")
    public String getAllPostsByBoard(@PathVariable String board_name, Model model) {
        int board_no = boardService.getBoardNumberByName(board_name);


        model.addAttribute("posts", postService.getAllPostsByBoard(board_no));
        model.addAttribute("board_no", board_no);
        model.addAttribute("board_name", board_name);

        String viewName = "junhyuk/posts/" + board_name.toLowerCase();
        return viewName;
    }

    @GetMapping("/{board_name}/posts/create")
    public String showCreateForm(@PathVariable("board_name") String board_name, Model model) {
        int board_no = boardService.getBoardNumberByName(board_name);

        model.addAttribute("board_no", board_no);
        model.addAttribute("post", new Post());
        model.addAttribute("board_name", board_name);
        return "junhyuk/posts/create";
    }



    @PostMapping("/{board_name}/posts")
    public String createPost(@PathVariable("board_name") String board_name,
                             @ModelAttribute Post post,
                             @RequestParam("files") MultipartFile[] files, // 배열로 변경
                             RedirectAttributes redirectAttributes) {
        int board_no = boardService.getBoardNumberByName(board_name);
        post.setBoard_no(board_no);
        int postNo = postService.createPost(post);

        for (MultipartFile file : files) { // 여러 파일을 처리하기 위한 반복문
            if (!file.isEmpty()) {
                try {
                    String originalFileName = file.getOriginalFilename();
                    String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

                    String storedFileName = UUID.randomUUID().toString() + fileExtension;

                    Path path = Paths.get("C:\\upload\\" + storedFileName);
                    Files.createDirectories(path.getParent());
                    Files.copy(file.getInputStream(), path);

                    // 파일 메타데이터 저장
                    FileAttachment fileAttachment = new FileAttachment();
                    fileAttachment.setPost_no(postNo);
                    fileAttachment.setFile_name(storedFileName);
                    fileAttachment.setOriginal_file_name(originalFileName);
                    fileAttachment.setFilePath(path.toString());
                    fileAttachment.setFileSize((int) file.getSize());
                    fileAttachment.setFileUploadedDate(LocalDateTime.now());

                    fileService.save(fileAttachment);

                } catch (IOException e) {
                    e.printStackTrace();
                    redirectAttributes.addFlashAttribute("message", "파일 업로드에 실패했습니다: " + e.getMessage());
                    return "redirect:/boards/" + board_name + "/posts/create";
                }
            }
        }

        if (files.length > 0) {
            redirectAttributes.addFlashAttribute("message", "게시글 및 파일이 성공적으로 업로드 되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 업로드 되었습니다.");
        }

        return "redirect:/boards/" + board_name + "/posts";
    }





    @GetMapping("/{board_name}/posts/{post_no}")
    public String viewPost(@PathVariable String board_name, @PathVariable("post_no") int post_no, Model model) {
        Post post = postService.findByPostNo(post_no);
        List<FileAttachment> attachments = fileService.findAttachmentsByPostNo(post_no);
        model.addAttribute("post", post);
        model.addAttribute("attachments", attachments);
        return "junhyuk/posts/view";

    }













}
