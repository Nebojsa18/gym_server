/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trainingsession;

import domain.TrainingSession;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class DeleteTrainingSession extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof TrainingSession)){
            throw new Exception("Invalid receipt data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((TrainingSession)param);
    }
    
}
