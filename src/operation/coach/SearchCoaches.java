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
public class SearchCoaches extends AbstractGenericOperation{
    
    List<Coach> coaches;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try{
        String query= (String) param;
        coaches = repository.getByQuery(new Coach(),query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading coaches", e);
        }
    }

    public List<Coach> getCoaches() {
        return coaches;
    }
    
}
