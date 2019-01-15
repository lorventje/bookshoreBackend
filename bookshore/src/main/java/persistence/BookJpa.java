package persistence;

import IDaos.IBookDao;
import domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class BookJpa implements IBookDao {

    private final EntityManager em;

    public BookJpa(EntityManager em){
        this.em = em;
    }

    public void create(Book book){
        em.persist(book);
    }

    public List<Book> getAllBooks() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Book.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Book> findBooksByTitle(String title){
        Query q = em.createNamedQuery("Book.findBooksByTitle", Book.class);
        q.setParameter("title", title);
        return (List<Book>) q.getResultList();
    }

    public Book findBookById(Integer id) {
        Query q = em.createNamedQuery("Book.findBookById", Book.class);
        q.setParameter("bookId", id);
        return (Book) q.getSingleResult();
    }
}
