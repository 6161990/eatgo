package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.EmailNotExistedException;
import kr.co.yeoeulsim.eatgo.application.PasswordWrongException;
import kr.co.yeoeulsim.eatgo.application.UserService;
import kr.co.yeoeulsim.eatgo.domain.User;
import kr.co.yeoeulsim.eatgo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    @Test
    public void createWithValidAttribute() throws Exception {
        String email = "tester@example.com";
        String password = "test";
        Long id = 1004L;
        String name = "Tester";

        User mockUser  = User.builder().id(id).name(name).level(1L).build();
        given(userService.authenticate(email, password)).willReturn(mockUser);
        given(jwtUtil.createToken(id,name, null))
                .willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createRestaurantOwner() throws Exception {
        String email = "tester@example.com";
        String password = "test";
        Long id = 1004L;
        String name = "Tester";

        User mockUser  = User.builder()
                .id(id)
                .name(name)
                .level(50L)
                .restaurantId(369L)
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(id,name, 369L ))
                .willReturn("header.payload.signature");

        mvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email), eq(password));
    }

    @Test
    public void createWithNotExistEmail() throws Exception {
        given(userService.authenticate("x@example.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"x@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@example.com"), eq("test"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("tester@example.com", "x"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"), eq("x"));
    }

}


/**
 * JWT : Json Web Tokens
 * Json ????????? ???????????? Web?????? ?????? ??? ?????? AccessToken ??????.
 * 3??????  ???????????? ??????????????????.
 *  1. Header : ?????? ????????????, ?????? ??????????????? ????????? ?????????
 *  2. Payload : ????????? ?????? ???????????? ??????????????? , ????????? ?????? ???????????? ???????????? ??????????????????????????? Web????????? ???????????? ????????? ???????????? ????????? ??????????????? ?????????.
 *  3. Signature : ??? ?????????(= ??????)??? ????????????????????? ???????????? ???????????? ?????? ?????????.
 *                 ???????????? ????????? ?????? ?????? ???????????? ??????????????? ????????? ??? ?????? ????????? ????????? ???????????? ???????????? ????????? ?????????.
 *                 ?????? ???????????? ????????? HMAC-SHA256 (= SHA256)??????.
 *
 * ????????? JWT??? Json ???????????? ?????????????????????,
 * Base64 URL Encoding??? ????????? ????????? ???????????? ????????? ?????????.
 *
 * Claims : Payload??? ?????? ???????????? ??????.
 * ?????? ???????????? ??? ?????? ex. userId.. userName... ?????? ?????? ?????? ?????? ????????????.
 * ???????????? ?????? ???????????? ?????? ?????? ?????? ??????.
 *
 * */