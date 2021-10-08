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
public class RenameMeResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MainFacade FACADE = MainFacade.getMainFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        
        
        //System.out.println("--------------->"+count);
        return "{\"count\":"+"}";  //Done manually so no need for a DTO
    }
}
