package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class FoodIngredientsTest {

    @Test
    public void settingFoodIngredientsGrams() throws NoSuchFieldException, IllegalAccessException {

        FoodIngredients foodIngredients = new FoodIngredients();
        foodIngredients.setName("Suger");
        foodIngredients.setGram(500);

        Field kcal = FoodIngredients.class.getDeclaredField("kcal");
        kcal.setAccessible(true);
        kcal.set(foodIngredients, 199);
        //Integer userLevel = (Integer) kcal.get(foodIngredients); get()하지 않아도

        assertThat(foodIngredients.getKcal()).isEqualTo(199);
    }

}