package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void create() {
        Category category = Category.builder().name("Korean Food").build();

        assertEquals(category.getName(), "Korean Food");
    }

}