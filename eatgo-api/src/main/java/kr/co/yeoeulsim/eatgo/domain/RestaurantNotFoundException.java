package kr.co.yeoeulsim.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id){
        super("Could not Found Restaurant Id" + id);
    }
}
