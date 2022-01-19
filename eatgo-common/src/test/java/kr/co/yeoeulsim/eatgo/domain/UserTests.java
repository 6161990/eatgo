package kr.co.yeoeulsim.eatgo.domain;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("tester@example.com")
                .name("테스터")
                .build();

        assertThat(user.getName()).isEqualTo("테스터");
        assertThat(user.isAdmin()).isEqualTo(true);
        assertThat(user.isActive()).isTrue();

        user.deactivate();

        assertThat(user.isActive()).isFalse();
    }

    @Test
    public void modifyUserLevel() throws NoSuchFieldException, IllegalAccessException {
        User user = User.builder()
                .email("tester@example.com")
                .name("테스터")
                .build();

        Field level = User.class.getDeclaredField("level");
        level.setAccessible(true);
        level.set(user, 100L);

        assertThat(user.getLevel()).isEqualTo(100L);
    }
}
