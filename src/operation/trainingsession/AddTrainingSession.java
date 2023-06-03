/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trainingsession;

import domain.AbstractDO;
import domain.TrainingSession;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class AddTrainingSession extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof TrainingSession)){
            throw new Exception("Invalid training session data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        TrainingSession ts = (TrainingSession) repository.add(param);
        for (int i = 0; i < ts.getNumberOfBountObject(); i++) {
            AbstractDO abstractDO = (AbstractDO) ts.getBoundObject(i, 0);
            abstractDO.setForeignId(ts.getId());
            repository.add(abstractDO);
        }
    }
    
}
