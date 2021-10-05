
import dtos.PersonDTO;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import utils.EMF_Creator;

//package facades;
//
//import dtos.PersonDTO;
//import entities.Person;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//
////import errorhandling.RenameMeNotFoundException;
//import utils.EMF_Creator;
//
///**
// *
// * Rename Class to a relevant name Add add relevant facade methods
// */
public class FacadeExample {
//
    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO CreatePerson() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("INSERT INTO PERSON SET FIRSTNAME = 'admin', LASTNAME = 'admin', EMAIL = 'admin@admin.com'");
            
            int createPerson = q.executeUpdate();
            System.out.println("-------------------------------------------------");
            System.out.println("You have inserted: " + createPerson);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
    }
    
    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person rm = em.find(Person.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
    }
    
//    public List<PersonDTO> getAll(){
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Person> query = em.createQuery("SELECT r FROM RenameMe r", Person.class);
//        List<Person> rms = query.getResultList();
//        return PersonDTO.getDtos(rms);
//    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = getFacadeExample(emf);
//        fe.getAll().forEach(dto->System.out.println(dto));
        fe.CreatePerson();
    }


//
//
}
