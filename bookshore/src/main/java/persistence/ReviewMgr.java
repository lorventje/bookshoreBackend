package persistence;

import IDaos.IBookDao;
import IDaos.IReviewDao;
import domain.Book;
import domain.Review;
import domain.Reviewer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class ReviewMgr {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    public ReviewMgr(){}

    public List<Review> getReviewsById(Integer id) {
        EntityManager em = emf.createEntityManager();
        IReviewDao iReviewDao = new ReviewJpa(em);
        List<Review> reviews = null;
        em.getTransaction().begin();
        try {
            reviews = iReviewDao.getReviewsById(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return reviews;
    }

    public Boolean createReview(String bookReview, Date date, Reviewer reviewer, Book book){
        EntityManager em = emf.createEntityManager();
        IReviewDao iReviewDao = new ReviewJpa(em);
        Review review = new Review();
        review.setBookReview(bookReview);
        review.setDate(date);
        review.setReviewer(reviewer);
        review.setBook(book);
        em.getTransaction().begin();
        try{
            iReviewDao.createReview(review);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public Boolean createReviewer(String name, String email){
        EntityManager em = emf.createEntityManager();
        IReviewDao iReviewDao = new ReviewJpa(em);
        Reviewer reviewer = new Reviewer();
        reviewer.setName(name);
        reviewer.setEmail(email);
        //reviewer.setReviews(reviews);
        em.getTransaction().begin();
        try{
            iReviewDao.createReviewer(reviewer);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }finally {
            em.close();
        }
        return true;
    }
}