package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.UserService;
import kr.co.yeoeulsim.eatgo.domain.User;
import kr.co.yeoeulsim.eatgo.utils.JwtUtil;
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
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(
            @RequestBody SessionRequestDto resource
            ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();
        User user = userService.authenticate(email, password);
        String accessToken = jwtUtil.createToken(
                user.getId(),
                user.getName(),
                user.isRestaurantOwner() ? user.getRestaurantId() : null);

        return ResponseEntity.created(new URI("/session")).body(
                SessionResponseDto
                        .builder()
                        .accessToken(accessToken)
                        .build()
                );
    }
}
