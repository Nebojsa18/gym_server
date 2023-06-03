/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.coach;

import domain.Coach;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class EditCoach extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Coach)){
            throw new Exception("Invalid member data");
        }else{
             Coach c=(Coach) param;
             checkValueConstraints(c);
         }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit((Coach)param);
    }
    
    private boolean checkIfExists(Coach coach) throws Exception {
        
        List<Coach> coaches= repository.getByQuery(new Coach(), "");
        
        for(Coach c: coaches){
            if(c!=null){
                if(c.equalsWithoutID(coach)){
                    return true;
                }
            }
        } 
        return false;
    }

    private void checkValueConstraints(Coach c) throws Exception {
        boolean exists= checkIfExists(c);
       if(exists){
            throw new Exception("Coach with that ID already exists. Try again.");
       }
    }
    
}
