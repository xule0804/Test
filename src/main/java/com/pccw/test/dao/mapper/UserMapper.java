package com.pccw.test.dao.mapper;

import com.pccw.test.dao.pojo.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    Integer checkAndUpdateUserIfExists(@Param("login_name") String loginName);

    void saveUser(@Param("userPo") UserPo userPo);

    UserPo selectUserById(@Param("userId") Integer userId);

    UserPo selectUserByLoginName(@Param("loginName") String loginName);

    int updateUserInfoById(@Param("userPo") UserPo userPo, @Param("userId") Integer userId);

    int updateUserStByUserId(@Param("userId") Integer userId, @Param("isDeleted") String isDeleted);

    void deleteAllUsers();
}
