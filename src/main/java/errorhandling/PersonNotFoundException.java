/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

/**
 *
 * @author Christoffer
 */
// used in editPerson to see if it can find the person inside the database
public class PersonNotFoundException extends Exception{
    public PersonNotFoundException(String message){
        super(message);
    }
    
}
