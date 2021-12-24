package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class EatgoApplicationTests {


    @Test
    void creation() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(restaurant.getName(), "Bob zip");
        assertEquals(restaurant.getAddress(), "Seoul");
    }

    @Test
    public void information() {
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        assertEquals(restaurant.getInformation(), "Bob zip in Seoul");
    }

}

/**
 * application, domain, interfaces 각각의 역할에 따라 test 해야 할 내용이 다르다.
 * 각 클래스에 있는 메소드 뿐만 아니라 메소드와 메소드 사이의 유기적인 기능과 역할을 검증할 수 있어야한다.*/
