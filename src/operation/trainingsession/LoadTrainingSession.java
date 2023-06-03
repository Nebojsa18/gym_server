/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trainingsession;

import domain.TrainingItem;
import domain.TrainingSession;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class LoadTrainingSession extends AbstractGenericOperation{
    private TrainingSession ts;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof TrainingSession)){
            throw new Exception("Invalid member data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.ts = null;
        
        List<TrainingSession>tsessions = repository.getAll((TrainingSession)param);
        
        if(!tsessions.isEmpty()){
            for (TrainingSession tsession : tsessions) {
                if(tsession.equals((TrainingSession)param)){
                    this.ts=tsession;
                }
            }
        }
        //prolazi kroz tabelu sa itemima i vraca one sa istim session id
        List<TrainingItem> items= repository.getByQuery(new TrainingItem(), " WHERE ti.sessionid= "+ts.getId()); 

        ts.setTrainingItems(items);
    }

    public TrainingSession getTrainingSession() {
        return ts;
    }
}
