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
public class GetAllTrainingSessions extends AbstractGenericOperation{
    
    List<TrainingSession> sessions;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        sessions = repository.getAll((TrainingSession)param);
        
        for(TrainingSession r: sessions){
                List<TrainingItem> items= repository.getByQuery(new TrainingItem(), " WHERE ti.sessionid= "+r.getId()); //prolazi kroz tabelu sa itemima i vraca one sa istim id kao session
                r.setTrainingItems(items);
            }
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }
    
    
    
}
