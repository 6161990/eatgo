package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FreeCondition extends AbstractSpecification {

    private final Free free;

    @Override
    public boolean isSatisfiedBy(Factor factor) {
        return factor.getFree().equals(free);
    }
}