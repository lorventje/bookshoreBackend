package persistence;

import IDaos.IBookDao;
import domain.Book;
import persistence.BookJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookMgr {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    public BookMgr(){}

    public boolean addBook(String title, Integer pages, String ISBN){
        EntityManager em = emf.createEntityManager();
        IBookDao iBookDao = new BookJpa(em);
        Book book = new Book();
        book.setTitle(title);
        book.setPages(pages);
        book.setISBN(ISBN);
        em.getTransaction().begin();
        try{
            iBookDao.create(book);
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
    public List<Book> getAllBooks(){
        EntityManager em = emf.createEntityManager();
        IBookDao iBookDao = new BookJpa(em);
        List<Book> books = null;
        em.getTransaction().begin();
        try {
            books = iBookDao.getAllBooks();
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        return books;
    }
    public List<Book> findBooksByTitle(String title){
        EntityManager em = emf.createEntityManager();
        IBookDao iBookDao = new BookJpa(em);
        List<Book> books = null;
        em.getTransaction().begin();
        try {
            books = iBookDao.findBooksByTitle(title);
            em.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return books;
    }
    public Book getBook(Integer id){
        EntityManager em = emf.createEntityManager();
        IBookDao iBookDao = new BookJpa(em);
        Book book = null;
        em.getTransaction().begin();
        try {
            book = iBookDao.findBookById(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return book;
    }
}
