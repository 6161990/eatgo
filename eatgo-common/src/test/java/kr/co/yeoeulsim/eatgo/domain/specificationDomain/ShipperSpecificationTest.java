package kr.co.yeoeulsim.eatgo.domain.specificationDomain;

import kr.co.yeoeulsim.eatgo.specificationDomain.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipperSpecificationTest {

    @Test
    void TREVARI_NO_DONE_SpecificationTest() {
        Factor factor = new Factor(Blame.TREVARI, Free.NO, ShipStatus.DONE);
        int calculator = calculator(factor);

        Assertions.assertEquals(calculator, 0);
    }

    public int calculator(Factor factor){
        if(new BlameCondition(Blame.TREVARI)
                .and(new FreeCondition(Free.YES).or(new FreeCondition(Free.NO)))
                .and(new ShipStatusCondition(ShipStatus.DONE).or(new ShipStatusCondition(ShipStatus.NOT))).isSatisfiedBy(factor)){
            return 0;
        }else if (new BlameCondition(Blame.CLIENT)
                .and(new FreeCondition(Free.YES)
                        .and(new ShipStatusCondition(ShipStatus.DONE))).isSatisfiedBy(factor)){
            return 2500;
        }else if (new BlameCondition(Blame.CLIENT)
                .and(new FreeCondition(Free.NO)
                        .and(new ShipStatusCondition(ShipStatus.DONE))).isSatisfiedBy(factor)){
            return 5000;
        }
        return 1000;
    }
}
