/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trainingsession;

import domain.TrainingSession;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class SearchTrainingSessions extends AbstractGenericOperation{
    List<TrainingSession> sessions;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        try{
        String query= (String) param;
        
        sessions = repository.getByQuery(new TrainingSession(),query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while loading the members", e);
        }
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }
}
