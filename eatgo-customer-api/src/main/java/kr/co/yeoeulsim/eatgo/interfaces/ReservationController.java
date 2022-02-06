package kr.co.yeoeulsim.eatgo.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReservationController {

    @PostMapping("/restaurants/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            @PathVariable Long restaurantId
    ) throws URISyntaxException {
        String url = "/restaurants/"+ restaurantId +"/reservations/1";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
