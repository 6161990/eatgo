package kr.co.yeoeulsim.eatgo.specificationDomain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AndSpecification extends AbstractSpecification {

    private Specification left;
    private Specification right;

    @Override
    public boolean isSatisfiedBy(Factor factor) {
        return left.isSatisfiedBy(factor) && right.isSatisfiedBy(factor);
    }

}
