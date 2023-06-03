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
public class GetAllCoaches extends AbstractGenericOperation{
    
    List<Coach> coaches;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        coaches = repository.getAll((Coach)param);
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
    
    
    
}
