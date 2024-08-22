package com.pccw.test.service.impl;

import com.pccw.test.dao.mapper.UserMapper;
import com.pccw.test.dao.pojo.UserPo;
import com.pccw.test.service.MailService;
import com.pccw.test.service.UserService;
import com.pccw.test.service.result.UserError;
import com.pccw.test.service.result.UserRegisterResult;
import com.pccw.test.web.vo.User;
import com.pccw.test.web.vo.UserEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;


/**
 * @Author：xu.le
 * @Package：com.pccw.test.service.impl
 * @Project：Test
 * @name：UserServiceImpl
 * @Date：2024/8/20 13:36
 * @Filename：UserServiceImpl
 **/
@Component
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    public UserRegisterResult register(User user) {

        UserRegisterResult rs = new UserRegisterResult();

        try {
            //1. First, check if the user exists.
            Integer checkRs = userMapper.checkAndUpdateUserIfExists(user.getLoginName());
            logger.info("checkRs=" + checkRs);
            if (checkRs != null && checkRs == 1) {
                logger.info("User already exists,loginName=" + user.getLoginName());
                rs.setSuccess(false);
                rs.setUserError(UserError.DUPLICATE_REGISTER);
                return rs;
            }

            //2. Register the user.
            UserPo uPo = this.covertToUserPo(user);
            userMapper.saveUser(uPo);
            rs.setUser_id(uPo.getUser_id());

            //3. Send email
            //The implementation of this service has been verified and will report an error if executed,
            // because the sender's account password is removed, so comment it out.
            //mailService.sendMail(user.getEmail(), user.getNickName());

            rs.setSuccess(true);
            return rs;
        } catch (DuplicateKeyException e) {
            logger.error("User already exists,loginName=" + user.getLoginName(), e);
            rs.setSuccess(false);
            rs.setUserError(UserError.DUPLICATE_REGISTER);
            return rs;
        } catch (Exception e) {
            logger.error("Unknown error,loginName=" + user.getLoginName(), e);
            rs.setSuccess(false);
            rs.setUserError(UserError.UNKNOWN_ERROR);
            return rs;
        }
    }

    @Override
    public User read(Integer userId) {
        logger.info("Read user, userId=" + userId);
        UserPo userPo = userMapper.selectUserById(userId);

        if (userPo == null) {
            logger.info("User not be found, userId=" + userId);
            return null;
        } else {
            User user = this.covertToUserVo(userPo);
            logger.info("Read user, user= " + user);
            return user;
        }
    }

    @Override
    public boolean edit(Integer userId, UserEdit userEdit) {
        logger.info("Edit user, userId=" + userId);


        UserPo userPo = this.covertUserEditToUserPo(userEdit);

        // update database
        int rs = userMapper.updateUserInfoById(userPo, userId);
        logger.info("rs=" + rs);

        return rs == 1;
    }

    @Override
    public boolean delete(Integer userId) {
        logger.info("Delete user, userId=" + userId);
        int rs = userMapper.updateUserStByUserId(userId, "1");
        logger.info("Delete user, userId=" + userId + " ,rs=" + rs);
        return rs == 1;
    }


    public UserPo covertToUserPo(User user) {
        UserPo userPo = new UserPo();

        userPo.setLogin_name(user.getLoginName());
        userPo.setPassword(user.getPassWord());
        userPo.setMobile(user.getMobile());
        userPo.setEmail(user.getEmail());
        userPo.setNick_name(user.getNickName());

        return userPo;
    }

    public UserPo covertUserEditToUserPo(UserEdit userEdit) {
        UserPo userPo = new UserPo();

        userPo.setPassword(userEdit.getPassWord());
        userPo.setMobile(userEdit.getMobile());
        userPo.setEmail(userEdit.getEmail());
        userPo.setNick_name(userEdit.getNickName());

        return userPo;

    }

    public User covertToUserVo(UserPo userPo) {
        User userVo = new User();

        userVo.setLoginName(userPo.getLogin_name());
        userVo.setPassWord(userPo.getPassword());
        userVo.setMobile(userPo.getMobile());
        userVo.setEmail(userPo.getEmail());
        userVo.setNickName(userPo.getNick_name());


        return userVo;
    }


}
