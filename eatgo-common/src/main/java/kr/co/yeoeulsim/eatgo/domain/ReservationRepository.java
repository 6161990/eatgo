package kr.co.yeoeulsim.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findAll();
    Reservation save(Reservation reservation);
}