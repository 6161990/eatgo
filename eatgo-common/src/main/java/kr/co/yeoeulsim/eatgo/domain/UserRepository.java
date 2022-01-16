package kr.co.yeoeulsim.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
