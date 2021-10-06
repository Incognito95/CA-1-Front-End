package facades;

import dtos.PersonDTO;
import entities.Person;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class MainFacade {
    private static MainFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
//    public MainFacade() {
//    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MainFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MainFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public PersonDTO getHobbyByPerson() throws Exception {
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            Query q = em.createNativeQuery("SELECT ID, FIRSTNAME, NAME FROM PERSON INNER JOIN HOBBY WHERE NAME = \"Basketball\"");
            List<Object[]> getHobbyByPerson = q.getResultList();
            System.out.println("-------------------------------------------------");
            System.out.println("List of hobbies by a person:");
            for (Object[] a : getHobbyByPerson) {
                System.out.println(Arrays.toString(a));
            }

                em.getTransaction().commit();
            } finally {
                em.close();
            }
        return new PersonDTO();
    }
    
    
    public PersonDTO CreatePerson() {
        
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("INSERT INTO PERSON SET FIRSTNAME = 'admin', LASTNAME = 'admin', EMAIL = 'admin@admin.com'");
            
            int createPerson = q.executeUpdate();
            System.out.println("-------------------------------------------------");
            System.out.println("You have inserted: " + createPerson + " person into the database");
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        emf = EMF_Creator.createEntityManagerFactory();
        MainFacade fe = getFacadeExample(emf);
        fe.CreatePerson();
        fe.getHobbyByPerson();
    }


  
}
