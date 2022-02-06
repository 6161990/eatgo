package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void create() throws Exception {
        Long userId = 1L;
        String name = "Tester";
        String date = "2019-12-14";
        String time = "20:00";
        Integer partySize = 20;

        mvc.perform(post("/restaurants/1004/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"name\":\"Tester\",\"date\":\"2019-12-14\",\"time\":\"20:00\",\"partySize\":20}"))
                .andExpect(status().isCreated());

        verify(reservationService).addReservation(
                eq(1004L), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }

}