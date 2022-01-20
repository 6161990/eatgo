package kr.co.yeoeulsim.eatgo.domain;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class FoodIngredients { // 김셰프 레시피 속 재료 정보

    @Getter @Setter
    private String name; //Sugar

    @Getter @Setter
    private Integer gram; //20g

    @Getter // @Setter LOOK!!
    protected double kcal; //200kcal

    protected double setKcal(Integer amount, double kcal) {
      int totalGram = amount * gram; // 1인분당 20g
      return totalGram * kcal; // 1g당 N kcal..
    }

}
