package kr.co.yeoeulsim.eatgo.specificationDomain;

public interface Specification<FACTOR> {
    Specification and(Specification other);
    Specification or(Specification other);
    Specification not();
    boolean isSatisfiedBy(FACTOR factor);
}
