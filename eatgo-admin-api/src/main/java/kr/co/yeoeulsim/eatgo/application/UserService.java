package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.User;
import kr.co.yeoeulsim.eatgo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(String email, String name) {
        User user = User.builder().email(email).name(name).build();
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
        // TODO : orElse 예외처리 restaurantService 예외 처리 참고
        User user = userRepository.findById(id).orElse(null);

        user.setEmail(email);
        user.setLevel(level);
        user.setName(name);

        return user;
    }
}
