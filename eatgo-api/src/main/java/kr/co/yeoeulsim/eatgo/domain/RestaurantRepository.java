package kr.co.yeoeulsim.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id); //레스토랑이 있냐 없냐를 반환(null을 반환하지 않음)

    Restaurant save(Restaurant any);
}
