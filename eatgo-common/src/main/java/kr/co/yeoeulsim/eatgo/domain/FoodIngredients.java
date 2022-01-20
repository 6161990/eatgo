package kr.co.yeoeulsim.eatgo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FoodIngredients {

    @Setter @Getter
    private String name;

    @Setter @Getter
    private Integer gram;

    //@Setter @Getter LOOK!!
    @Getter
    private Integer kcal;

}
