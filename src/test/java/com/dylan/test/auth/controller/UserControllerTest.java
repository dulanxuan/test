package com.dylan.test.auth.controller;

import com.dylan.test.auth.common.consts.ExceptionConstant;
import com.dylan.test.auth.service.encryption.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ComponentScan("com.dylan.test.auth")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EncryptionService encryptionService;

    @Test
    public void testAddUser() throws Exception {
        String requestBody = "{\"userId\": \"123456\", \"endpoint\":[\"/user/a\",\"/user/b\",\"/user/c\"]}";
        mockMvc.perform(post("/admin/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getAdminToken())
                .content(requestBody))
                .andExpect(status().isOk());
    }

    private String getAdminToken() throws ExceptionConstant.EncryptionException {
        String successString = "{\"id\":\"adminId\", \"accountName\":\"管理员\", \"role\": \"admin\" }";
        return encryptionService.encode(successString);
    }

    private String getUserToken() throws ExceptionConstant.EncryptionException {
        String successString = "{\"id\":\"123456\", \"accountName\":\"用户\", \"role\": \"user\" }";
        return encryptionService.encode(successString);
    }

    @Test
    public void testUserResource() throws Exception {
        mockMvc.perform(get("/user/a").header("Authorization", getUserToken()))
                .andExpect(status().isOk());
    }


}
