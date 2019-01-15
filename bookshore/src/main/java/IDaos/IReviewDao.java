package IDaos;

import domain.Review;
import domain.Reviewer;

import java.util.List;

public interface IReviewDao {
    List<Review> getReviewsById(Integer id);
    void createReviewer(Reviewer reviewer);
    void createReview(Review review);
}
