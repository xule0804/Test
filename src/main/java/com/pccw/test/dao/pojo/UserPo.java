package com.pccw.test.dao.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author：xu.le
 * @Package：com.pccw.test.dao.pojo
 * @Project：Test
 * @name：UserPo
 * @Date：2024/8/20 13:40
 * @Filename：UserPo
 **/
@Data
public class UserPo {

    private int user_id;

    private String login_name;

    private String password;

    private String mobile;

    private String email;

    private String nick_name;

    private String is_deleted;

    private Date recCrtTs;

    private Date recUpdTs;

}
