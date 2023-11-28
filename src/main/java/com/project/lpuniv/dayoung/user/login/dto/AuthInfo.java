package com.project.lpuniv.dayoung.user.login.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInfo {
    private int user_no;
    private Integer user_tp;
    private String user_loginId;
    private String user_passwd;

}
