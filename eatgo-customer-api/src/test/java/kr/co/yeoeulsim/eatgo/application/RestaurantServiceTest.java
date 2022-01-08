package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RestaurantServiceTest {
// 스프링이 아니라 일반적인 테스트 이므로 @Autowired를 사용할 수 없음.
// (setUp) 직접 연결할 수 있도록 BeforeEach => MockitoAnnotations
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp(){
        /*  얘네를 지우는 대신 openMocks 해주어야한다. 객체를 할당해준다.
        restaurantRepository = new RestaurantRepositoryImpl();
        menuItemRepository = new MenuItemRepositoryImpl();  */

        MockitoAnnotations.openMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = MenuItem.builder()
                .name("KimChi")
                .build();
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

        given(restaurantRepository.findAllByAddressContaining("Seoul")).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("BeRyong")
                .score(2)
                .description("Bad")
                .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }


    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertEquals(restaurant.getId(), 1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertEquals(menuItem.getName(), "KimChi");

        Review review = restaurant.getReviews().get(0);
        assertEquals(review.getDescription(), "Bad");

    }

    @Test
    public void getRestaurantWithNotExisted(){
        Throwable exception = assertThrows(RestaurantNotFoundException.class, ()->{
            throw new RestaurantNotFoundException(404L);
        });
        assertEquals(exception.getMessage(), "Could not Found Restaurant Id 404");
    }

    @Test
    public void getRestaurants(){
        String region ="Seoul";
        List<Restaurant> restaurants = restaurantService.getRestaurants(region);

        Restaurant restaurant = restaurants.get(0);
        assertEquals(restaurant.getId(), 1004L);
    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeBe")
                .address("Busan")
                .build();

        Restaurant createRestaurant = restaurantService.addRestaurant(restaurant);

        assertEquals(createRestaurant.getId(), 1234L);
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1234L)
                .name("BeBe")
                .address("Busan")
                .build();

        given(restaurantRepository.findById(1234L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1234L, "Sool zip", "Busan");

        assertEquals(restaurant.getName(), "Sool zip");
        assertEquals(restaurant.getAddress(), "Busan");
    }

}