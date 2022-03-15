package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotSpecification extends AbstractSpecification {

    private Specification spec;

    @Override
    public boolean isSatisfiedBy(Factor factor) {
        return !spec.isSatisfiedBy(factor);
    }

}
