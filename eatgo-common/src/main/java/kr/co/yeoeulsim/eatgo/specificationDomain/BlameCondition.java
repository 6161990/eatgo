package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlameCondition extends AbstractSpecification {

    private final Blame blame;

    @Override
    public boolean isSatisfiedBy (Factor factor){
        return factor.getBlame().equals(blame);
    }
}
