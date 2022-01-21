package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

class FoodIngredientsTest {

    @Test // org.springframework.test.util.ReflectionTestUtils
    public void modifyAddIngredientsGram(){

        FoodIngredients foodIngredients = new FoodIngredients();
        foodIngredients.setName("Sugar");
        foodIngredients.setGram(20);
        
        ReflectionTestUtils.setField(foodIngredients, "kcal", 12); //private 변수인 kcal setting
        double kcal = foodIngredients.getKcal(); // 12
        double calculatorKcal = ReflectionTestUtils.invokeMethod(foodIngredients, "setKcal", 3 , kcal);

        assertThat(kcal).isEqualTo(12);
        assertThat(calculatorKcal).isEqualTo(720.0);
    }

    @Test // java.lang.reflect.Field
    public void settingSugarKcal() throws NoSuchFieldException, IllegalAccessException {

        FoodIngredients foodIngredients = new FoodIngredients();
        foodIngredients.setName("Sugar");
        foodIngredients.setGram(180);

        Field kcal = FoodIngredients.class.getDeclaredField("kcal"); // 변수명
        kcal.setAccessible(true); // 접근 가능하도록
        kcal.set(foodIngredients, 10); //reflect를 이용해 private 변수 세팅

        assertThat(foodIngredients.getKcal()).isEqualTo(10); //  세팅한 값이 그대로 들어있는지 확인
    }


}