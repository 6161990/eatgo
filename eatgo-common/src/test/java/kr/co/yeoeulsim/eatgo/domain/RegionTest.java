package kr.co.yeoeulsim.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    public void creation() {
        Region region = Region.builder().name("서울").build();

        assertEquals(region.getName(), "서울");
    }

}