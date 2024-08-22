package com.pccw.test;

import com.pccw.test.dao.mapper.UserMapper;
import com.pccw.test.dao.pojo.UserPo;
import com.pccw.test.service.UserService;
import com.pccw.test.web.controller.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    private static final String LoginName = "john";

    private static final Integer userId = 9;

    @Autowired
    private MockMvc mockMvc;

    @Mock  // Mock  UserService
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @InjectMocks  // UserController
    private UserController userController;

    // Register a user and check if the registration was successful.
    @Test
    public void testRegisterUser() throws Exception {

        ///1. Clean up the database.
        userMapper.deleteAllUsers();

        //2. Register a user.
        String userJson = "{\"loginName\":\"john\",\"passWord\":\"xule76!\",\"mobile\":\"15901929667\",\"email\":\"xule0804@gmail.com\",\"nickName\":\"John smith\"}";

        // send POST request，and validate response
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value("SUCCESS"))
                .andExpect(jsonPath("$.errorMsg").isEmpty());

        // Query the persistence layer to see if the data has been saved.
        UserPo userPo = userMapper.selectUserByLoginName(LoginName);

        assertNotNull(userPo);
        assertEquals(LoginName, userPo.getLogin_name());
        assertEquals("15901929667", userPo.getMobile());
    }

    @Test
    public void testReadUser() throws Exception {

        //1. Clean up the database.
        userMapper.deleteAllUsers();

        //2. Insert a record and get UserId.
        UserPo userPo = new UserPo();
        userPo.setLogin_name("lili");
        userPo.setPassword("test123");
        userPo.setMobile("15901929444");
        userPo.setNick_name("lex");
        userPo.setEmail("mylifstyle125@gmail.com");

        userMapper.saveUser(userPo);
        Integer userId = userPo.getUser_id();

        //3. test restful
        mockMvc.perform(get("/api/users/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value("SUCCESS"))
                .andExpect(jsonPath("$.errorMsg").isEmpty())
                .andExpect(jsonPath("$.data.loginName").value("lili"))
                .andExpect(jsonPath("$.data.passWord").value("test123"))
                .andExpect(jsonPath("$.data.mobile").value("15901929444"))
                .andExpect(jsonPath("$.data.email").value("mylifstyle125@gmail.com"))
                .andExpect(jsonPath("$.data.nickName").value("lex"));
    }

    @Test
    public void testDeleteUser() throws Exception {

        //1. Clean up the database.
        userMapper.deleteAllUsers();

        //2. Insert a record and get UserId.
        UserPo userPo = new UserPo();
        userPo.setLogin_name("lili");
        userPo.setPassword("test123");
        userPo.setMobile("15901929001");
        userPo.setNick_name("lex");
        userPo.setEmail("mylifstyle125@gmail.com");

        userMapper.saveUser(userPo);
        Integer userId = userPo.getUser_id();


        // 3. Put Request.
        mockMvc.perform(put("/api/users/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value("SUCCESS"))
                .andExpect(jsonPath("$.errorMsg").isEmpty())
                .andExpect(jsonPath("$.data").isEmpty());


        //4. Check result.
        UserPo deleteUser = userMapper.selectUserById(userId);

        assertNotNull(deleteUser);
        assertEquals("1", deleteUser.getIs_deleted());//1- soft delete

    }

    @Test
    public void testEditUser() throws Exception {
        String loginName = "editTest2";

        //1. Clean up the database.
        userMapper.deleteAllUsers();

        //2. Insert a record and get UserId.
        UserPo userPo = new UserPo();
        userPo.setLogin_name("lili");
        userPo.setPassword("test123");
        userPo.setMobile("15901929668");
        userPo.setNick_name("lex");
        userPo.setEmail("mylifstyle125@gmail.com");

        userMapper.saveUser(userPo);
        Integer userId = userPo.getUser_id();


        // 3. Patch Request.
        String userEditJson = "{ \"passWord\":\"china76!4\", \"mobile\":\"13641878420\", \"email\":\"xule0804@gmail.com\", \"nickName\":\"john smith\" }";

        mockMvc.perform(patch("/api/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userEditJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.respCode").value("SUCCESS"))
                .andExpect(jsonPath("$.errorMsg").isEmpty())
                .andExpect(jsonPath("$.data").isEmpty());


        UserPo editUser = userMapper.selectUserById(userId);

        assertNotNull(editUser);
        assertEquals("13641878420", editUser.getMobile());

    }

}
