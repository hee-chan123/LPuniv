package com.project.lpuniv.junhyuk.dto;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileAttachment {

    private int post_no;
    private String file_name;
    private String original_file_name;
    private String filePath;
    private int fileSize;
    private LocalDateTime fileUploadedDate;


}
