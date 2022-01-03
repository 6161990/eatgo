package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.RestaurantService;
import kr.co.yeoeulsim.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/restaurants") /** 모든 레스토랑 가져오기 */
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}") /** 특정 레스토랑 가져오기 */
    public Restaurant detail(@PathVariable("id")Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id); // 기본 정보 + 메뉴 정보
        // try catch 로 묶는 방법 보다는 @controllerAdvice

        return restaurant;
    }

}
