package facades;

import entities.Hobby;
import utils.EMF_Creator;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class FacadeTest {

    private static EntityManagerFactory emf;
    private static FacadeExample facade;
    
    
    public FacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
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
            
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate(); 
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            
            // create person after deleting data
            Person p1 = new Person("admin", "admin", "admin@admin.com");
            Person p2 = new Person("admin 2", "admin 2", "admin2@admin.com");
            Person p3 = new Person("admin 3", "admin 3", "admin3@admin.com");
            
            Phone phone1 = new Phone(1234, "Admin 1 telefonnummer");
            Phone phone2 = new Phone(12356, "Admin 2 telefonnummer");
            Phone phone3 = new Phone(12356, "Admin 3 telefonnummer");
            
            Hobby hobby1 = new Hobby("Painting", "description here");
            Hobby hobby2 = new Hobby("Working Out", "description here");
            Hobby hobby3 = new Hobby("Writing", "desciption here");
            
            em.persist(hobby1);
            em.persist(hobby2);
            em.persist(hobby3);
            
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            
            em.persist(phone1);
            em.persist(phone2);
            em.persist(phone3);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @Test
    public void testAddPerson() throws Exception {
        
    }
    
    
    
    
    

    // executed after each @Test method in the current test class.
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
    
    
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.CreatePerson());
//        assertEquals(2, facade.c());
    }
    
    
    @Test
    public void testSearchForNumberOfPeopleWithHobby() throws Exception {
        
    }

}
