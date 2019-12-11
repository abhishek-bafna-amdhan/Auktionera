package se.iths.auktionera.business.service;

import se.iths.auktionera.business.model.Review;
import se.iths.auktionera.business.model.ReviewRequest;

public interface IReviewService {
    Review createSellerReview(ReviewRequest review, Long id);
    Review createBuyerReview(ReviewRequest review, Long id);

    void checkAccountAgainstSellerId(ReviewRequest reviewRequest, Long id);

}
