package kr.co.yeoeulsim.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    List<MenuItem> findAllByRestaurantId(Long restaurantId);
    void deleteById(Long id); // id가 아니라 엔티티를 전부 넣어줘서 삭제할 때는 id에 지정한 타입으로 return 된다.
}
