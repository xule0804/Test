package com.pccw.test.web.vo;

import lombok.Data;

/**
 * Base Reponse
 *
 * @Author：xu.le
 * @Package：com.pccw.test.web.vo
 * @Project：Test
 * @name：Response
 * @Date：2024/8/20 11:28
 * @Filename：Response
 **/
@Data
public class Response {

    private String respCode = "SUCCESS";

    private String errorMsg;

    private int userId;

    private Object data;
}
