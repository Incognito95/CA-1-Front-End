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
import javax.ws.rs.WebApplicationException;
import javax.persistence.TypedQuery;

import dtos.PersonDTO;
import entities.Hobby;
import entities.Person;
import java.util.ArrayList;
import utils.EMF_Creator;

import java.util.Arrays;
import java.util.List;


    public class MainFacade {
    private static MainFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public MainFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MainFacade getMainFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MainFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


        

    public PersonDTO getHobbyByPerson() {
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


    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
        if (rm == null)
           // throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
        return null;
    }

    
    public PersonDTO getAllPersonsByCiytOrZip()
    {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            Query q = em.createNativeQuery("SELECT FIRSTNAME, COUNTRYNAME, ZIPCODE FROM PERSON INNER JOIN CITY");
            List<Object[]> listOfPeopleByCityOrZip = q.getResultList();
            System.out.println("-------------------------------------------------");
            System.out.println("List of people by city or zip:");
            for (Object[] a : listOfPeopleByCityOrZip) {
                System.out.println(Arrays.toString(a));
            }

                em.getTransaction().commit();
            } finally {
                em.close();
            }
        return new PersonDTO();
    }
    

    public PersonDTO editPerson()
    {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("UPDATE PERSON SET FIRSTNAME = 'admin', LASTNAME = 'admin', EMAIL = 'admin@admin.com' WHERE ID = 1");
            
            int updatedPerson = q.executeUpdate();
            System.out.println("-------------------------------------------------");
            System.out.println("You updated a person with ID number: " + updatedPerson);
            

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
    }
   
  
    
    public PersonDTO getAmountOfPeopleWithHobby() {
            EntityManager em = emf.createEntityManager();
            
            try {
                em.getTransaction().begin();

                Query q = em.createNativeQuery("SELECT COUNT(FIRSTNAME) FROM PERSON INNER JOIN HOBBY WHERE NAME = NAME");
                List<Object[]> AmountOfPeopleWithHobby = q.getResultList();
                System.out.println("-------------------------------------------------");
                System.out.println("The amount of people with a hobby is: " + AmountOfPeopleWithHobby);
                System.out.println("-------------------------------------------------");
                for (Object[] a : AmountOfPeopleWithHobby) {
                    System.out.println(Arrays.toString(a));
                }
                
                em.getTransaction().commit(); 
            } finally {                
                em.close();
            }
            return new PersonDTO();
    }
    
    
    public static void main(String[] args) throws Exception {
        emf = EMF_Creator.createEntityManagerFactory();
        MainFacade fe = getMainFacade(emf);
        fe.CreatePerson();
        fe.getHobbyByPerson();
        fe.editPerson();
        fe.getAllPersonsByCiytOrZip();
        fe.getAmountOfPeopleWithHobby();
    }
 
}
