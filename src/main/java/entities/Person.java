package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQueries({
    @NamedQuery(name = "Person.getAllRows", query = "select p from Person p")})

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    
    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Phone> phone;
    
    
    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Hobby> hobbies;
    
    @ManyToOne
    private Address address;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName, String email, List<Hobby> hobbies, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hobbies = new ArrayList();
        this.address = null;
        this.phone = new ArrayList();
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public Person() {
    }
    
<<<<<<< HEAD
    public void addPerson(Person person) {
        if (person != null) {
            person.addPerson(person);
            }
    }
=======
>>>>>>> 43bcd89eaebd6a94dcb7844e20f112fb69047d53

    public void addHobby(Hobby hobby) {
        if (hobby != null) { // if hobby doesn't exist insert hobby into person table
            this.hobbies.add(hobby); // add hobby to table
            hobby.getPersons().add(this); // add hobby into person table
        }
    }
    
    public void addNumberToPerson(Phone phone) {
        if (phone != null) { // if phone number doesn't exist insert phone number into person table
            phone.setPerson(this); // add phone number to address table
            this.phone.add(phone); // add phone number to table
        }
    }
    
    public void removePhone(Phone phone) {
        if (phone != null){
            this.phone.remove(phone);
            phone.setPerson(this);
        }
    }
    
    public void removeHobby(Hobby hobby) {
        if(hobby != null){
            this.hobbies.remove(hobby);
            hobby.getPersons().remove(this);
        }
    }
    
}