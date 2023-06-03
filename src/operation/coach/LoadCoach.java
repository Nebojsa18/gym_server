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
public class LoadCoach extends AbstractGenericOperation{
    private Coach coach;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Coach)){
            throw new Exception("Invalid member data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.coach = null;
        
        List<Coach>coaches = repository.getAll((Coach)param);
        
        if(!coaches.isEmpty()){
            for (Coach coach : coaches) {
                if(coach.equals((Coach)param)){
                    this.coach=coach;
                }
            }
        }
    }

    public Coach getCoach() {
        return coach;
    }
    
    
}
