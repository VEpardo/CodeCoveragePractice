package com.codecoverage.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.codecoverage.practice.dao.UserRepository;
import com.codecoverage.practice.model.User;
import com.codecoverage.practice.service.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends TestCase {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    User user1 = new User(55, "Melissa", 11, "USA");
    User user2 = new User(48, "Emilio", 33, "Canada");
    User user3 = new User(1, "Violeta", 22, "Mexico");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsers() throws Exception{
        List<User> usersrecord = new ArrayList<>(Arrays.asList(user1,user2,user3));
        Mockito.when(userService.getUsers()).thenReturn(usersrecord);
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUserByAddress() throws Exception{
        List<User> usersrecord = new ArrayList<>(Arrays.asList(user1,user2,user3));
        Mockito.when(userService.getUserbyAddress(user1.getAddress())).thenReturn(usersrecord);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void saveUser() throws Exception{
        User record = User.builder()
                .id(4)
                .name("Violeta")
                .age(22)
                .address("Mexico")
                .build();
        Mockito.when(userService.addUser(record)).thenReturn(record);
        String content = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void deleteUser() throws Exception{
//        User record = User.builder()
//                .id(4)
//                .name("Violeta")
//                .age(22)
//                .address("Mexico")
//                .build();
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/user/remove")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }


}