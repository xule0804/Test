package com.pccw.test.service.result;

import lombok.Data;


@Data
public class UserRegisterResult {

    private boolean success;

    private int user_id;

    private UserError userError;

}
