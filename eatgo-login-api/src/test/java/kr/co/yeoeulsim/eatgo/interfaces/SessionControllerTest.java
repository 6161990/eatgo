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
 * Json 포맷을 이용해서 Web에서 다룰 수 있는 AccessToken 표준.
 * 3개의  부분으로 이루어져있다.
 *  1. Header : 어떤 타입인지, 어떤 알고리즘을 가지고 있는지
 *  2. Payload : 실제로 어떤 데이터가 담겨있는지 , 여기에 담긴 데이터는 암호화가 되어있지않기때문에 Web상에서 노출되면 안되는 데이터는 여기에 담겨있으면 안된다.
 *  3. Signature : 이 데이터(= 토큰)가 위변조되어있지 않았음을 증명하는 서명 데이터.
 *                 일정하게 해싱을 하고 해싱 데이터를 암호화하여 나중에 그 값을 비교해 변하지 않았는지 확인하는 절차를 거친다.
 *                 그때 이용하는 방법이 HMAC-SHA256 (= SHA256)이다.
 *
 * 그런데 JWT는 Json 포맷으로 이루어져있지만,
 * Base64 URL Encoding을 이용해 일정한 문자열로 바꾸어 나간다.
 *
 * Claims : Payload에 담긴 데이터를 뜻함.
 * 실제 사용하게 될 것들 ex. userId.. userName... 토큰 유효 시간 등이 담겨있다.
 * 가능하면 많은 데이터를 담지 않는 것이 좋다.
 *
 * */