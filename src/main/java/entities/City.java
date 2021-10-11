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
import javax.persistence.OneToMany;

/**
 *
 * @author danielpedersen
 */
@Entity
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ZipCode;
    private String countryName;

    // Primary keys
    // one to many associates relationship between tables
    // mapped by distinguishes the owner of the table ie. primary key
    // cascading is used to link two entities or more together (parent child relationship)
    @OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
    private List<Address> addresses;

    // constructors
    public City() {}

    public City(Long id, int ZipCode, String countryName) {
        this.id = id;
        this.ZipCode = ZipCode;
        this.countryName = countryName;
        this.addresses = new ArrayList();
    }

    // getters and setters
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // AddAdress method - checking if address contains any address if not then adds it to the list
    public void AddAddress(Address address) {
        if (address != null) { // if address doesn't exist insert address into person table
            address.setCity(this); // add city to address table
            this.addresses.add(address); // add address to table
        }
    }
}


