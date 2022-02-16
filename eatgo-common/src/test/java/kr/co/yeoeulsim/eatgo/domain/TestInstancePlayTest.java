package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 생명주기는 클래스
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 순서에 의해서 테스트 실행
@ExtendWith(TestInstancePlay.class) // 커스텀 Extension Import
class TestInstancePlayTest {

    private AtomicLong count = new AtomicLong(0L);

    @BeforeEach
    public void setUp() {
        count.getAndAdd(1); // 테스트 실행 전마다 +1
    }

    @Test
    @Order(1) // 처음 실행되는 테스트
    public void test1() {
        assertThat(count).hasValue(1L);
    }

    @Test
    @Order(2) // 두번째에 실행되는 테스트
    public void test2() {
        assertThat(count).hasValue(2L); //2L
    }

}