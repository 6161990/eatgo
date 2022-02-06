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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotEmpty
    private String email;

    @Setter
    @NotEmpty
    private String name;

    private String password;

    private Long restaurantId;

    @Setter
    @NotNull
    private Long level;

    public boolean isAdmin() {
        return level >= 3;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
        level = 0L;
    }

    public void setRestaurantId(Long restaurantId) {
        this.level = 50L;
        this.restaurantId = restaurantId;
    }

    public boolean isRestaurantOwner() {
        return level == 50L;
    }
}
