package kr.co.yeoeulsim.eatgo.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @NullSource
    @EmptySource // @NullAndEmptySource
    @ValueSource(strings = {" ","  ", "\t", "\n"})
    public void repeatTestUserInvalidEmail(String text) {
        User user = User.builder()
                .email(text)
                .build();
        String email = user.getEmail();
        assertTrue(email == text);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ","  ", "\t", "\n"})
    public void repeatTestUserInvalidEmail2(String text, TestReporter testReporter) {
        User user = User.builder()
                .email(text)
                .build();
        String email = user.getEmail();
        assertTrue(email+ "TEXT"  == text);
    }

    @DisplayName("반복되는 테스트마다 인수를 알려줍니다.")
    @ParameterizedTest(name = "{index} ==> Email can't contains '{arguments}'")
    @ValueSource(strings = {"@","!", "*", "#"})
    public void repeatTestUserInvalidEmail3(String text) {
        User user = User.builder()
                .email(text)
                .build();
        String email = user.getEmail();
        assertTrue(email.concat("@") == text);
    }

    @DisplayName("")
    @ParameterizedTest(name = "{index} ==> Email can't contains '{arguments}'")
    @ValueSource(strings = {"@","!", "*", "#"})
    public void repeatTestUserInvalidEmail4(String text) {
        User user = User.builder()
                .email(text)
                .build();
        String email = user.getEmail();
        assertTrue(email.concat("@") == text);
    }

}
