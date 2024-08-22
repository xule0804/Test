package com.pccw.test.web.controller;

import com.pccw.test.service.UserService;
import com.pccw.test.service.result.UserRegisterResult;
import com.pccw.test.util.EmailValidator;
import com.pccw.test.util.PhoneNumberValidator;
import com.pccw.test.web.vo.Response;
import com.pccw.test.web.vo.User;
import com.pccw.test.web.vo.UserEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * A restful controller implements register user, read user, edit user and delete user.
 *
 * @Author：xu.le
 * @Package：com.pccw.test.web.controller
 * @Project：Test
 * @name：UserController
 * @Date：2024/8/20 11:28
 * @Filename：UserController
 **/
@RestController
public class UserController {

    public final static String USER_ALEADY_EXIST = "USER_ALEADY_EXIST";
    public final static String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    public final static String USER_NOT_FOUND = "USER_NOT_FOUND";
    public final static String USER_NOT_EXIST = "USER_NOT_EXIST";
    public final static String BAD_REQUEST = "BAD_REQUEST";
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    //a. Register a new user.
    @PostMapping("/api/users")
    public Response registerUser(@RequestBody User user) {

        Response rs = new Response();
        if (!PhoneNumberValidator.isValidPhoneNumber(user.getMobile())
                || !EmailValidator.isValidEmail(user.getEmail())) {
            rs.setRespCode(BAD_REQUEST);
            return rs;
        }

        try {
            logger.info("Start registering user, loginName=" + user.getLoginName());

            // Register the user.
            UserRegisterResult urs = userService.register(user);
            if (!urs.isSuccess()) {
                logger.info("Registration successful, loginName=" + user.getLoginName() + ",error=" + rs.getRespCode());
                rs.setRespCode(USER_ALEADY_EXIST);
                return rs;
            }
            rs.setUserId(urs.getUser_id());
            logger.info("Registration finished, response=" + rs);
            return rs;

        } catch (Exception e) {
            logger.error("Reading error, loginName=" + user.getLoginName(), e);
            rs.setRespCode(UNKNOWN_ERROR);
            return rs;
        }
    }

    // b. Read user by userId.
    @GetMapping(value = "/api/users/{userId}")
    public Response readUser(@PathVariable Integer userId) {

        Response rs = new Response();


        try {
            logger.info("Start reading user, userId=" + userId);
            User user = userService.read(userId);
            if (user == null) {
                rs.setRespCode(USER_NOT_FOUND);
            } else {
                rs.setUserId(userId);
                rs.setData(user);
            }
            logger.info("Reading finished, userId=" + userId);
            return rs;
        } catch (Exception e) {
            logger.error("Reading error, userId=" + userId, e);
            rs.setRespCode(UNKNOWN_ERROR);
            return rs;
        }
    }

    // c. Edit user with userId.
    @PatchMapping(value = "/api/users/{userId}")
    public Response editUser(@PathVariable Integer userId, @RequestBody UserEdit userEdit) {

        Response rs = new Response();


        if (!PhoneNumberValidator.isValidPhoneNumber(userEdit.getMobile())
                || !EmailValidator.isValidEmail(userEdit.getEmail())) {
            rs.setRespCode(BAD_REQUEST);
            return rs;
        }

        try {
            logger.info("Start editing user, userId=" + userId + " ,userEdit=" + userEdit);
            boolean success = userService.edit(userId, userEdit);
            logger.info("Editing finished, userId=" + userId + ",success=" + success);
            if (success) {
                rs.setUserId(userId);
            }else{
                rs.setRespCode(USER_NOT_EXIST);
            }
            return rs;
        } catch (Exception e) {
            logger.error("Editing error, userId=" + userId, e);
            rs.setRespCode(UNKNOWN_ERROR);
            return rs;
        }
    }

    // d. Delete user with userId. This is a soft delete.
    @PutMapping(value = "/api/users/{userId}")
    public Response deleteUser(@PathVariable Integer userId) {

        Response rs = new Response();
        try {
            logger.info("Start deleting user, userId=" + userId);
            boolean success = userService.delete(userId);
            logger.info("Deleting finished, loginName=" + userId + " ,success=" + success);
            if (success) {
                rs.setUserId(userId);
            }else{
                rs.setRespCode(USER_NOT_EXIST);
            }
            return rs;
        } catch (Exception e) {
            logger.error("Deleting error, userId=" + userId, e);
            rs.setRespCode(UNKNOWN_ERROR);
            return rs;
        }
    }


}
