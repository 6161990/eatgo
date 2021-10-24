package kr.co.yeoeulsim.eatgo;

import kr.co.yeoeulsim.eatgo.domain.Restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EatgoApplicationTests {


    @Test
    void creation() {
        Restaurant restaurant = new Restaurant("bob","seoul");
        Assertions.assertEquals(restaurant.getName(), "bob");
        Assertions.assertEquals(restaurant.getAddress(), "seoul");
    }

    @Test
    public void information() {
        //given
        Restaurant restaurant = new Restaurant("bob","seoul");
        //when

        //then
        Assertions.assertEquals(restaurant.getInformation(), "bob in seoul");
    }

}
