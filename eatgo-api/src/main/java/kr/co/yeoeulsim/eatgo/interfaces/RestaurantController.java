package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.RestaurantService;
import kr.co.yeoeulsim.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id")Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id); // 기본 정보 + 메뉴 정보
        // try catch 로 묶는 방법 보다는 @controllerAdvice

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build());

        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant resource){
        restaurantService.updateRestaurant(id, resource.getName(), resource.getAddress());
        return "{}";
    }

}
