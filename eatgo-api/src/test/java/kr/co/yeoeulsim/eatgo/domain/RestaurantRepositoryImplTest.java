package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryImplTest {

    @Test
    public void save(){
        RestaurantRepository repository = new RestaurantRepositoryImpl();

        int oldCount = repository.findAll().size();

        Restaurant restaurant = new Restaurant("BeBe", "seoul");

        repository.save(restaurant);

        Assertions.assertEquals(restaurant.getId(), 1234L);

        int newCount = repository.findAll().size();

        Assertions.assertEquals(newCount - oldCount, 1);
    }

}