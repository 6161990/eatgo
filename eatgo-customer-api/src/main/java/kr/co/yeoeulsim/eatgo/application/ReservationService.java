package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.Reservation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {

    public Reservation addReservation(Long restaurantId, Long userId, String name, String date, String time, Integer partySize){
        return  null;
    }
}
