/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trainingsession;

import domain.AbstractDO;
import domain.TrainingItem;
import domain.TrainingSession;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class EditTrainingSession extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof TrainingSession)){
            throw new Exception("Invalid training session data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        TrainingSession ts = (TrainingSession) param;
        repository.edit(ts);
        for (int i = 0; i < ts.getTrainingItems().size(); i++) {
            //AbstractDO abstractDO;
            AbstractDO abstractDO = (AbstractDO) ts.getBoundObject(i, 0);
            switch (abstractDO.getAction()) {
                case ADD:
                    abstractDO.setForeignId(ts.getId());
                    repository.add(abstractDO);
                    break;
                case DELETE:
                    repository.delete(abstractDO);
                    break;
                case UPDATE:
                   repository.edit(abstractDO);
                   break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
}
