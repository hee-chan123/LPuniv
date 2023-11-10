package com.project.lpuniv.Juchan.OCC.Dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OccDto {
    int OCC_NO;
    int teach_NO;
    String OCC_title;
    String OCC_content;
    Date OCC_date;
    Date OCC_Mdate;
    int OCC_ROT;
    String OCC_teachInfo;
    String OCC_kakao;
}
