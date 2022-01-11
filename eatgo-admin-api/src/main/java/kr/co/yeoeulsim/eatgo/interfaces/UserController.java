package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.UserService;
import kr.co.yeoeulsim.eatgo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        List<User> users = userService.getUsers();

        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {
        String name = resource.getName();
        String email = resource.getEmail();

        User user = userService.addUser(email, name);

        return ResponseEntity.created(new URI("/users"+ user.getId())).body("{}");
    }

    @PatchMapping("/users/{id}")
    public String update(
            @PathVariable("id") Long id,
            @RequestBody User resource
    ) {
        String name = resource.getName();
        String email = resource.getEmail();
        Long level = resource.getLevel();

        userService.updateUser(id, email, name, level);
        return "{}";
    }
}
