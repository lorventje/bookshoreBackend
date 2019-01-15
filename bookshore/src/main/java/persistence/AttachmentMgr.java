package persistence;

import IDaos.IBookDao;
import domain.Attachment;
import domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AttachmentMgr {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    public AttachmentMgr(){}

    /*public Attachment getAttachment(Integer id){
        EntityManager em = emf.createEntityManager();
        IAttachmentDao iAttachmentDao = new IAttachmentDao(em);
        Attachment attachment = null;
        em.getTransaction().begin();
        try {
            attachment = iAttachmentDao.findAttachmentById(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return book;
    }*/
}
