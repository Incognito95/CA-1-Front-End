package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<PersonDTO> all = new ArrayList<PersonDTO>();

    public PersonDTO(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public PersonDTO(){}
    
    public PersonDTO(Person rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.firstName = rm.getFirstName();
        this.lastName = rm.getLastName();
        this.email = rm.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<PersonDTO> getAll() {
        return all;
    }

    public void setAll(List<PersonDTO> all) {
        this.all = all;
    }

    public static List<PersonDTO> getDtos(List<Person> rms){
        List<PersonDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new PersonDTO(rm)));
        return rmdtos;
    }

    public PersonDTO(List<Person> personEntities){
        personEntities.forEach((p) -> {
            all.add(new PersonDTO(p));
        });
    }



}
