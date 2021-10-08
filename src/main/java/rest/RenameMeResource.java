package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.MainFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static junit.runner.Version.id;
//Todo Remove or change relevant parts before ACTUAL use

@Path("Persons")
public class RenameMeResource {
    
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MainFacade FACADE = MainFacade.getMainFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    //How to use @pathParam in order to have multiple paths
//    @Path("adress")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getFirtName(@PathParam("firstName") String firstName) {
//        return GSON.toJson(p1);
//        }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getPersonCount();
        return "{\"count\":" + count +"}";
    }
    
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
        PersonDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }
    
    
    @Path("id")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById() {
        List<Person> getById = FACADE.getById(1);
        return GSON.toJson(getById);
    }
    
    @Path("amount")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAmountOfPeopleWithHobby() {
        long hobbyCount = FACADE.getAmountOfPeopleWithHobby();
        return "{\"count\":" + hobbyCount +"}";
    }
    

    
    
    
    
}
