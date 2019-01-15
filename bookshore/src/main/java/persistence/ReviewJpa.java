package persistence;

import IDaos.IReviewDao;
import domain.Book;
import domain.Review;
import domain.Reviewer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ReviewJpa implements IReviewDao {

    private final EntityManager em;

    public ReviewJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Review> getReviewsById(Integer id){
        Query q = em.createNamedQuery("Review.getReviewsById", Review.class);
        q.setParameter("id", id);
        return (List<Review>) q.getResultList();
    }

    public void createReviewer(Reviewer reviewer) {
        em.persist(reviewer);
    }
    public void createReview(Review review){
        em.persist(review);
    }
}
