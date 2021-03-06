package facades;

import dtos.PersonDTO;
import entities.Hobby;
import utils.EMF_Creator;
import entities.Person;
import entities.Phone;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MainFacadeTest {

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


    public MainFacadeTest() {
    }

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
    
    @Test
    void getPersonCount() {
        p1.getLastName();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(99, p1.getFirstName());
    }
    
    @Test
    void testGetAllPersons() {
        p1.getFirstName();
         EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(99, p1.getFirstName());
    }
    
    @Test
    void testGetHobbyByPerson() {
        p1.addHobby(hobby1);
         EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(99, p1.getFirstName());
    }

    @Test
    void testCreatePerson() {
        p1.setFirstName("admin");
        p1.setLastName("admin 3");
        p1.setEmail("admin@admin.com");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
       assertEquals(1, p1.getFirstName() + p1.getLastName() + p1.getEmail());
    }
    
    @Test
    void testGetById() {
        p1.setId(Long.MIN_VALUE);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
       assertEquals(1, p1.getId());
    }

    @Test
    void testGetAllPersonsByCiytOrZip() {
        p1.setId(Long.MIN_VALUE);
        p1.addHobby(hobby2);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals(2, p1.getHobbies().size());
    }

    @Test
    void testEditPerson() throws PersonNotFoundException, MissingInputException {
        p1.setFirstName("Ermin");
        PersonDTO p = new PersonDTO(p1);
        EntityManager em = emf.createEntityManager();
        try {
            p = facade.editPerson(p);
        } finally {
            em.close();
        }
        assertEquals("Ermin", p1.getFirstName());
    }

    @Test
    void testGetAmountOfPeopleWithHobby() {
        p1.getFirstName();
        p1.getHobbies();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertEquals("admin", p1.getFirstName());
    }
    
    @Test
    public void testDeleteAPersonByID() throws Exception {
        long id = p1.getId();
        EntityManagerFactory emf = null;
        MainFacade mf = MainFacade.getMainFacade(emf);
        PersonDTO expectedResult = new PersonDTO(p1);
        boolean actualResult = mf.deleteAPersonById(id);
        PersonDTO getPersonsAfterDelete = mf.getAllPersons();
        assertEquals(2, getPersonsAfterDelete.getAll().size());
        assertThrows(PersonNotFoundException.class, () -> {
        mf.getById(id);
                });
        assertEquals(expectedResult, actualResult);
        
    }
  
}
