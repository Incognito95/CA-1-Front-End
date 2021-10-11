package dtos;

import entities.Hobby;
import facades.MainFacade;
import utils.EMF_Creator;
import entities.Person;
import entities.Phone;

import javax.persistence.*;

import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class IntegrationsTest {

    private static EntityManagerFactory emf;
    private static MainFacade facade;

    Person p1 = new Person("admin", "admin", "admin@admin.com");
    Person p2 = new Person("admin 2", "admin 2", "admin2@admin.com");
    Person p3 = new Person("admin 3", "admin 3", "admin3@admin.com");

    Phone phone1 = new Phone(1234, "Admin 1 telefonnummer");
    Phone phone2 = new Phone(12356, "Admin 2 telefonnummer");
    Phone phone3 = new Phone(12356, "Admin 3 telefonnummer");
    Phone phone4 = new Phone(123567, "Admin 3 telefonnummer");

    Hobby hobby1 = new Hobby("Painting", "description here");
    Hobby hobby2 = new Hobby("Working Out", "description here");
    Hobby hobby3 = new Hobby("Writing", "desciption here");




    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MainFacade.getMainFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class

    // execute before each @Test method in the current test class.
    // This deletes all data in each table in the database
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // Return the resource-level EntityTransaction object and commit multiple transactions.

            em.createQuery("DELETE FROM Phone").executeUpdate();
            em.createQuery("DELETE FROM Person").executeUpdate();
            em.createQuery("DELETE FROM City").executeUpdate();
            em.createQuery("DELETE FROM Address").executeUpdate();
            em.createQuery("DELETE FROM Hobby").executeUpdate();


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Test
    public long getPersonCount() {

        EntityManager em = getEntityManager();
        try {
            long personCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r ").getSingleResult();
            if (personCount < 3) {
                System.out.println("people count is 3");
            } else if (personCount > 3) {
                System.out.println("people count is not more than 3");
            }
            return personCount;
        }finally {
            em.close();
        }

    }


    @Test
    public PersonDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try{
            if (em.createNamedQuery("Person.getAllRows").getResultList().isEmpty()) {
                System.out.println("Person table is not empty");
            } else if (em.createNamedQuery("Person.getAllRows").getResultList().contains("admin") && em.createNamedQuery("Person.getAllRows").getResultList().contains("admin2") && em.createNamedQuery("Person.getAllRows").getResultList().contains("admin3")) {
                System.out.println("Person table contains all those people");
            }
            return new PersonDTO(em.createNamedQuery("Person.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    @Test
    public List<Hobby> getHobbyByPerson(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hobby> query = em.createQuery("select h from Hobby h join h.persons p WHERE p.id =:id", Hobby.class);

            if (getHobbyByPerson(1).equals("Basketball") && getHobbyByPerson(2).equals("Basketball")) {
                System.out.println("hobbies match");
            } else if (getHobbyByPerson(2).isEmpty() && getHobbyByPerson(2).isEmpty()) {
                System.out.println("hobbies don't match");
            }

            query.setParameter("id", id);
            System.out.println(getHobbyByPerson(1));
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Test
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

    // couldn't get it to work
//    @Test
//    public List<Person> getById(long id) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            TypedQuery<Person> query = em.createQuery("select p from Person p WHERE p.firstName = :id", Person.class);
//
//            if (getAllPersons().getFirstName() == getAllPersons().getId()) { // // their has to be getId but i'm not sure what number should go in it
//                assertEquals(1,100.000);
//            } else if (getAllPersons().getFirstName() != getAllPersons().getId(this) { // their has to be getId but i'm not sure what number should go in it
//               assertEquals();
//            }
//
//            query.setParameter("id", id);
//            return query.getResultList();
//
//        } catch (NoResultException ex) {
//            return new ArrayList<>();
//        }
//    }

    @Test
    public List<Person> getAllPersonsByZip(int zipcode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("select p from Person p join p.address a WHERE a.city.ZipCode=:zipcode", Person.class);

            if (getAllPersonsByZip(2450) != getAllPersons()) {
                assertEquals(2450, 2800);
            } else if (getAllPersonsByZip(2450) == getAllPersons()) {
                assertEquals(2450, 2450);
            }

            query.setParameter("zipcode", zipcode);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Test
    public PersonDTO editPerson() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("UPDATE PERSON SET FIRSTNAME = 'admin', LASTNAME = 'admin', EMAIL = 'admin@admin.com' WHERE ID = 1");

            if (editPerson().getFirstName() == "admin") {
                assertEquals("admin", "admin");
            } else if (editPerson().getFirstName() != "admin") {
                assertEquals("admin2", "admin");
            }

            int updatedPerson = q.executeUpdate();
            System.out.println("-------------------------------------------------");
            System.out.println("You updated a person with ID number: " + updatedPerson);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
    }

    @Test
    public long getAmountOfPeopleWithHobby() {
        EntityManager em = getEntityManager();
        try {
            long hobbyCount = (long) em.createQuery("SELECT COUNT(p.firstName) FROM Person p JOIN p.hobbies h WHERE h.persons.firstName = :firstname").getSingleResult();

            if (getAmountOfPeopleWithHobby() != 3) {
                assertEquals(3, 2);
            } else if (getAmountOfPeopleWithHobby() == 3) {
                assertEquals(3, 2);
            }

            System.out.println(hobbyCount);
            return hobbyCount;
        }finally {
            em.close();
        }
    }
    
    @Test
    public PersonDTO DeletePersonByID(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("DELETE FROM Person p WHERE p.id = :id").setParameter("id", id);

            int deletedPerson = q.executeUpdate();
            System.out.println("You deleted a person with ID number: " + deletedPerson);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
        
    }

}
