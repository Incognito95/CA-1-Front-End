/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author danielpedersen
 */
@Entity
// used to convert the state of an object into a byte stream in order to handle the data in our database
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // Primary keys
    // mapped by distinguishes the owner of the table ie. primary key
    // cascading is used to link two entities or more together (parent child relationship)
    // ManyToMany either side may be the owning side
    @ManyToMany
    private List<Person> persons;
    
    // constructors
    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
        this.persons = new ArrayList();
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    // addPerson method - checking if person contains any person if not then adds it to the list
    public void addPerson(Person person) {
        if (person != null) { // if person doesn't exist insert person into person table
            this.persons.add(person); // add person to table
            person.getHobbies().add(this); // add person into hobby table
        }
    }
}
