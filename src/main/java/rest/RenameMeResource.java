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
<<<<<<< HEAD
    
    @Path("{server}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String serverIsUp(@PathParam("server")String server){
        return "(\"msg\":\"Your server is up and running\")";
    }
    
=======

    //How to use @pathParam in order to have multiple paths
//    @Path("adress")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getFirtName(@PathParam("firstName") String firstName) {
//        return GSON.toJson(p1);
//        }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll(){
        PersonDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }

>>>>>>> fdc3850255ef8f8a2da94ece1a6c6042a414e52d
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getPersonCount();
        return "{\"count\":" + count +"}";
    }
}
