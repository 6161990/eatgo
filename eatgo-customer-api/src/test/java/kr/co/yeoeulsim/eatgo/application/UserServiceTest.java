package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.User;
import kr.co.yeoeulsim.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void registerUser() {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    public void registerUserWithExistedEmail() {
        String email = "tester@example.com";

        User mockUser = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        EmailExistedException existedException = assertThrows(EmailExistedException.class, () -> {
            throw new EmailExistedException(email);
        });

        verify(userRepository, never()).save(any());
        assertEquals(existedException.getMessage(), "Email is already registered: " + email );
    }

    @Test
    public void authenticateWithValidAttributes() {
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        User user = userService.authenticate(email, password);

        assertEquals(user.getEmail(), email);
    }

    @Test
    public void authenticateNotExistedEmail() {
        String email = "x@example.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        EmailNotExistedException exception = assertThrows(EmailNotExistedException.class, () ->
                userService.authenticate(email, password));

        assertEquals("Email is Not registered: " + email , exception.getMessage());
    }
}