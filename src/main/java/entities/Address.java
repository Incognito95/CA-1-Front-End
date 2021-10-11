/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author danielpedersen
 */
@Entity
// used to convert the state of an object into a byte stream in order to handle the data in our database
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String AdditionalInfo;

    // Primary keys
    // one to many associates relationship between tables
    // mapped by distinguishes the owner of the table ie. primary key
    // cascading is used to link two entities or more together (parent child relationship)
    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    private List<Person> persons;
    
    @ManyToOne
    private City city;

    // constructors
    public Address() {}

    public Address(String street, String AdditionalInfo) {
        this.street = street;
        this.AdditionalInfo = AdditionalInfo;
    }

    public Address(String street, City city) {
        this.street = street;
        this.city = city;
    }

    // getters and setters
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
