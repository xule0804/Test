package com.pccw.test.service;

import com.pccw.test.service.result.UserRegisterResult;
import com.pccw.test.web.vo.User;
import com.pccw.test.web.vo.UserEdit;

/**
 * @Author：xu.le
 * @Package：com.pccw.test.service
 * @Project：Test
 * @name：UserService
 * @Date：2024/8/20 13:40
 * @Filename：UserService
 **/
public interface UserService {

    /**
     * register user
     *
     * @param user
     * @return
     */
    UserRegisterResult register(User user);


    /**
     * read user
     *
     * @param userId
     * @return
     */
    User read(Integer userId);

    /**
     * edit user
     *
     * @param userId
     * @return
     * @Param userEdit
     */
    boolean edit(Integer userId, UserEdit userEdit);

    /**
     * delete user
     *
     * @param userId
     * @return
     */
    boolean delete(Integer userId);


}
