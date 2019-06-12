/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.controller;

import com.utp.teamwork.model.Branch;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.service.EmployeeService;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.context.support.WithMockUser;

/**
 *
 * @author AdamPrzedlacki
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        AuthenticationController authenticationController = new AuthenticationController();
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void isViewContainsSomething() throws Exception {

        mockMvc
                .perform(get("/loginError"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
