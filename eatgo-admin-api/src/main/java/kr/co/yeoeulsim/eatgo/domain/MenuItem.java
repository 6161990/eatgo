package kr.co.yeoeulsim.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Long restaurantId;

    private String name;

    @Transient // DB에 넣지 않겠다.
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) // boolean default = false : false가 아니면 포함해라
    private boolean destroy;


}
