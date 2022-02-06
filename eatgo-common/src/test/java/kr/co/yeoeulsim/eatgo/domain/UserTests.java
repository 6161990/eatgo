package kr.co.yeoeulsim.eatgo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("tester@example.com")
                .name("테스터")
                .level(100L)
                .build();

        assertThat(user.getName()).isEqualTo("테스터");
        assertThat(user.isAdmin()).isEqualTo(true);
        assertThat(user.isActive()).isTrue();

        user.deactivate();

        assertThat(user.isActive()).isFalse();
    }

    @Test
    public void restaurantOwner() {
        User user = User.builder()
                .level(1L)
                .build();

        assertThat(user.isRestaurantOwner()).isFalse();

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner()).isTrue();
        assertThat(user.getRestaurantId()).isEqualTo(1004L);
    }

}
