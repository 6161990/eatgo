package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {
// 스프링이 아니라 일반적인 테스트 이므로 @Autowired를 사용할 수 없음.
// (setUp) 직접 연결할 수 있도록 BeforeEach => MockitoAnnotations
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp(){
        /*  얘네를 지우는 대신 openMocks 해주어야한다. 객체를 할당해준다.
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();  */

        MockitoAnnotations.openMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem("Kimchi");
        menuItems.add(menuItem);


        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
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

    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant("BeBe", "busan");
        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("BeBe")
                .address("busan")
                .build();

        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant createRestaurant = restaurantService.addRestaurant(restaurant);

        Assertions.assertEquals(createRestaurant.getId(), 1234L);
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1234L)
                .name("BeBe")
                .address("busan")
                .build();

        given(restaurantRepository.findById(1234L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1234L, "Sool zip", "Busan");

        Assertions.assertEquals(restaurant.getName(), "Sool zip");
        Assertions.assertEquals(restaurant.getAddress(), "Busan");
    }

}