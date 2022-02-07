package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.Reservation;
import kr.co.yeoeulsim.eatgo.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation(){

        Long userId = 1004L;
        String name = "John";
        String date = "2019-12-14";
        String time = "20:00";
        Integer partySize = 20;

       // Reservation mockReservation = Reservation.builder().name(name).build();
       // given(reservationRepository.save(any())).willReturn(mockReservation);
        given(reservationRepository.save(any())).will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
        });

        Reservation reservation = reservationService.addReservation(
                369L, userId, name, date, time, partySize);

        assertEquals(reservation.getName(), name);

        verify(reservationRepository).save(any(Reservation.class));
    }

}