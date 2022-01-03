package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.Review;
import kr.co.yeoeulsim.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
