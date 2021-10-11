package facades;

import dtos.PersonDTO;
import entities.City;
import entities.Person;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.persistence.TypedQuery;

import dtos.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

import java.util.Arrays;
import java.util.List;


public class MainFacade {

    private static MainFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public MainFacade() {}

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

    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try {
            long personCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r ").getSingleResult();
            System.out.println(personCount);
            return personCount;
        } finally {
            em.close();
        }
    }

    public PersonDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try{
           return new PersonDTO(em.createNamedQuery("Person.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public List<Hobby> getHobbyByPerson(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Hobby> query = em.createQuery("select h from Hobby h join h.persons p WHERE p.id =:id", Hobby.class);
            query.setParameter("id", id);
            System.out.println(getHobbyByPerson(1));
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    public PersonDTO CreatePerson(String firstName, String lastName, String email, String phone, String street, String zip, String city) throws MissingInputException {
        if ((firstName.length() == 0 || (lastName.length() == 0))){
            throw new MissingInputException("Firstname and/or Lastname is missing");
        }
        EntityManager em = emf.createEntityManager();
        Person person = new Person(firstName, lastName, email);

        try {
            em.getTransaction().begin();
            Query q = em.createNativeQuery("SELECT a FROM Address a WHERE a.street = :street AND a.zip = :zip AND a.city = :city");
            q.setParameter("street", street);
            q.setParameter("city", city);
            List<Address> addresses = q.getResultList();
            if (addresses.size() > 0){
                person.setAddress(addresses.get(0));
            } else {
                person.setAddress(new Address(street, city));
            }
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO();
    }

    public List<Person> getById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("select p from Person p WHERE p.firstName = :id", Person.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    public List<Person> getAllPersonsByZip(int zipcode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("select p from Person p join p.address a WHERE a.city.ZipCode=:zipcode", Person.class);
            query.setParameter("zipcode", zipcode);
            return query.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    // not fully working yet
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException {
        if ((p.getFirstName().length() == 0) || (p.getLastName().length() == 0)){
            throw new MissingInputException("Firstname and/or Lastname is missing");
    }
        
        EntityManager em = emf.createEntityManager();
        
        try {
            Person person = em.find(Person.class, p.getId());
            if (person == null){
                throw new PersonNotFoundException(String.format("Person with id: (%d) not found. ", p.getId()));
            }
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            person.setEmail(p.getEmail());
                        
            em.getTransaction().commit();
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    public long getAmountOfPeopleWithHobby() {       
        EntityManager em = getEntityManager();
        try {
            long hobbyCount = (long) em.createQuery("SELECT COUNT(p.firstName) FROM Person p JOIN p.hobbies h WHERE p.firstName = :firstname").getSingleResult();
            System.out.println(hobbyCount);
            return hobbyCount;
        } finally {
            em.close();
        }
    }

    public boolean deleteAPersonById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person p WHERE p.id = :id").setParameter("id", id).executeUpdate();
            System.out.println("You deleted a person with the ID: " + id);
            em.getTransaction().commit();
            return true;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) throws Exception {
        emf = EMF_Creator.createEntityManagerFactory();
        MainFacade mf = getMainFacade(emf);
        mf.CreatePerson("firstName", "lastName", "email", "phone", "street", "zip", "city");
        mf.getHobbyByPerson(1);
//        mf.editPerson();
        mf.getAllPersonsByZip(1);
        mf.getAmountOfPeopleWithHobby();
        mf.getPersonCount();
    }
}
