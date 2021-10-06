package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.MainFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Persons")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(); 
    private static final MainFacade FACADE =  MainFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("firstName")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String firtName() {
        return "{\"msg\":\"Firstname says what\"}"; //How do we get a person's name in here my boys?
    }
    
    
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        
//        
//        //System.out.println("--------------->"+count);
//        return "{\"count\":"+"}";  //Done manually so no need for a DTO
//    }
}
