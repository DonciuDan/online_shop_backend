package com.online_shop_backend.services;

import com.online_shop_backend.exceptions.NotFoundException;
import com.online_shop_backend.models.User;
import com.online_shop_backend.models.UserRole;
import com.online_shop_backend.repositories.UserRepository;
import com.online_shop_backend.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest(){
        //definire date pentru testare
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

        //definire comportament pentru metoda find all din UserRepository
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        //apelul metodei findAll din userService
        List<User> resultList = userService.findAll();
        //verificare rezultate
        assertTrue(resultList.size()==2);
        assertEquals("user",resultList.get(0).getUsername());
    }

    @Test
    public void getUserByIdTest() throws NotFoundException {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user");
        user1.setEmail("user@email.com");
        user1.setPassword("1234");
        user1.setUserRole(UserRole.USER);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        User user = userService.findById(1);
        assertTrue(user != null);
        assertEquals(user1.getId(),user.getId());
    }

    @Test
    public void deleteUserByIdTest() throws NotFoundException {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user");
        user1.setEmail("user@email.com");
        user1.setPassword("1234");
        user1.setUserRole(UserRole.USER);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        userService.delete(1);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(1);

    }

    //TODO: Fix test to expect exception
    @Test
    public void deleteByIdTest() throws NotFoundException {
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user");
        user1.setEmail("user@email.com");
        user1.setPassword("1234");
        user1.setUserRole(UserRole.USER);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        userService.delete(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }
}
