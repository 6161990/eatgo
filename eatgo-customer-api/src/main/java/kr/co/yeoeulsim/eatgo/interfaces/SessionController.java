package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.SessionDto;
import kr.co.yeoeulsim.eatgo.application.UserService;
import kr.co.yeoeulsim.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionDto> create(
            @RequestBody User resource
            ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();
        userService.authenticate(email, password);
        return ResponseEntity.created(new URI("/session")).body(
                SessionDto
                        .builder()
                        .accessToken("ACCESSTOKEN")
                        .build()
                );
    }
}
