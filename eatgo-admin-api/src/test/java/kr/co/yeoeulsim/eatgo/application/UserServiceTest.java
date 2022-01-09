package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.CategoryRepository;
import kr.co.yeoeulsim.eatgo.domain.User;
import kr.co.yeoeulsim.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userService = new UserService();
    }

    @Test
    public void getUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("tester@example.com")
                .name("테스터")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        assertEquals(users.get(0).getName(), "테스터");
    }

}