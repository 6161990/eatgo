package kr.co.yeoeulsim.eatgo;

import kr.co.yeoeulsim.eatgo.domain.Restaurant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EatgoApplicationTests {


    @Test
    void creation() {
        Restaurant restaurant = new Restaurant(1004L, "Bob zip","seoul");

        Assertions.assertEquals(restaurant.getId(), 1004L);
        Assertions.assertEquals(restaurant.getName(), "Bob zip");
        Assertions.assertEquals(restaurant.getAddress(), "seoul");
    }

    @Test
    public void information() {
        //given
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","seoul");
        //when

        //then
        Assertions.assertEquals(restaurant.getInformation(), "Bob zip in seoul");
    }

}
