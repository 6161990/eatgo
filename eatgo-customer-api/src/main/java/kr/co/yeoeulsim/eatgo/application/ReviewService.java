package kr.co.yeoeulsim.eatgo.application;

import kr.co.yeoeulsim.eatgo.domain.Review;
import kr.co.yeoeulsim.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaur경antId, String name, Integer score,
                            String description) {
        Review review = Review.builder()
                .name(name)
                .score(score)
                .description(description)
                .build();

        return reviewRepository.save(review);
    }
}
