package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class RestaurantServiceTest {
// 스프링이 아니라 일반적인 테스트 이므로 @Autowired를 사용할 수 없음.
// (setUp) 직접 연결할 수 있도록 BeforeEach
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp(){
        /*  얘네를 지우는 대신 initMocks을 해주어야한다.
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();  */

        MockitoAnnotations.openMocks(this);

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }


    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        Assertions.assertEquals(restaurant.getId(), 1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        Assertions.assertEquals(menuItem.getName(), "Kimchi");

    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        Assertions.assertEquals(restaurant.getId(), 1004L);
    }

}