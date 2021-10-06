package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import utils.EMF_Creator;
import facades.MainFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Persons")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(); 
    private static final MainFacade FACADE =  MainFacade.getFacadeExample(EMF);
    private static PersonDTO p1 = new PersonDTO(1L,"firstname", "lastname", "email");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String person() {
        return GSON.toJson(p1);
    }
    
    //How to use @pathParam in order to have multiple paths
//    @Path("adress")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getFirtName(@PathParam("firstName") String firstName) {
//        return GSON.toJson(p1);
//        }
    
    
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
