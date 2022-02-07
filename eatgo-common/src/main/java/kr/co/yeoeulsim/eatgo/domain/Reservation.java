package kr.co.yeoeulsim.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Setter
    private String name;

    @Setter
    @NotEmpty
    private String date;

    @Setter
    @NotEmpty
    private String time;

    @Setter
    @NotNull
    private Integer partySize;
}