package com.project.lpuniv.minho.listenLec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LecListDto {
    private int CCIM_NO;
    private int OCC_NO;
    private String CCIM_title;
    private String CCIM_content;
    private String CCIM_videoID;
}
