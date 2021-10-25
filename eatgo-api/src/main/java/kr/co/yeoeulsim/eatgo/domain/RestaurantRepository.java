package kr.co.yeoeulsim.eatgo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {

    private  List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository(){
        restaurants.add(new Restaurant(1004L, "Bob zip","seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber Food","seoul"));
    }


    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .get();
    }
    // .orElse(null); get()대신 이렇게



}
