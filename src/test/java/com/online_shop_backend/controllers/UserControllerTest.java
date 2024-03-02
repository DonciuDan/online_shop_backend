package com.online_shop_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online_shop_backend.exceptions.InvalidDataException;
import com.online_shop_backend.models.User;
import com.online_shop_backend.models.UserRole;
import com.online_shop_backend.services.UserService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserTest() {
        //Baza de date virtuala
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@email.com");
        user.setPassword("1234");
        user.setUserRole(UserRole.USER);
        //metoda when ne ajuta sa returnam un anumit obiect cand o metoda este apelata
        try {
            Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(user);

            mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(user)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.data.username").value("user"))
                            .andExpect(jsonPath("$.data.email").value("user@email.com"));
        } catch (InvalidDataException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user");
        user1.setEmail("user@email.com");
        user1.setPassword("1234");
        user1.setUserRole(UserRole.USER);

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user");
        user2.setEmail("user@email.com");
        user2.setPassword("1234");
        user2.setUserRole(UserRole.USER);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", IsCollectionWithSize.hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[1].id").value(2));
    }
}
