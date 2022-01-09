package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.UserService;
import kr.co.yeoeulsim.eatgo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().email("tester@example.com")
                .name("테스터")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(userList);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("테스터")));
    }


}