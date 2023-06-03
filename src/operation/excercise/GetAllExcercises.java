/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.excercise;

import domain.Excercise;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class GetAllExcercises extends AbstractGenericOperation{

    List<Excercise> excercises;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        excercises = repository.getAll((Excercise)param);
    }

    public List<Excercise> getExcercises() {
        return excercises;
    }
    
    
    
}
