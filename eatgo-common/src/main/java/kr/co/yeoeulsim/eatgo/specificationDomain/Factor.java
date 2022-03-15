package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Factor {

    @Getter
    Blame blame;

    @Getter
    Free free;

    @Getter
    ShipStatus shipStatus;

}
