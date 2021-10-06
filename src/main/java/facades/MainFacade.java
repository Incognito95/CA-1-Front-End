package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;

import java.util.Arrays;
import java.util.List;


public class MainFacade {
    private static MainFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MainFacade() {
    }

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

    public PersonDTO CreatePerson() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("update Person p SET p.firstName =:firstname, p.lastName =:lastname, p.email =:email");

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
        if (rm == null)
           // throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(rm);
        return null;
    }

    public PersonDTO getAllPersonsByCiytOrZip()
    {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNativeQuery("");
            List<Object[]> getAllPersonsByCiytOrZip = q.getResultList();
            System.out.println("-------------------------------------");
            for (Object[] a : getAllPersonsByCiytOrZip)
                System.out.println(Arrays.toString(a));
            {
                em.getTransaction().commit();
            }
        }finally {
            em.close();
        }
        return new PersonDTO();
    }

    public PersonDTO editPerson()
    {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNativeQuery("");
            List<Object[]> editPerson = q.getResultList();
            System.out.println("-------------------------------------");
            for (Object[] a : editPerson)
                System.out.println(Arrays.toString(a));
            {
                em.getTransaction().commit();
            }
        }finally {
            em.close();
        }
        return new PersonDTO();
    }

//    public List<PersonDTO> getAll(){
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
//        List<Person> rms = query.getResultList();
//        return PersonDTO.getDtos(rms);
//    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MainFacade fe = getFacadeExample(emf);
//        fe.getAll().forEach(dto->System.out.println(dto));
//       fe.CreatePerson();
    }
}
