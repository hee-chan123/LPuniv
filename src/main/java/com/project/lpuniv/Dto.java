package com.project.lpuniv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Dto {
    int user_no;
    int user_tp;
    String user_nm;
    String user_tel;
    String user_loginID;
    String user_passwd;

    String user_email;
    String user_brdt;
    int user_gen;
    Date user_signdate;
    Date user_deletedate;

}
