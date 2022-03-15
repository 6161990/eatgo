package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipStatusCondition extends AbstractSpecification {

    private final ShipStatus shipStatus;

    @Override
    public boolean isSatisfiedBy(Factor factor) {
        return factor.getShipStatus().equals(shipStatus);
    }
}