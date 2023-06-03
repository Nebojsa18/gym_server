/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.member;

import domain.Member;
import domain.TrainingSession;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class DeleteMember extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Member)){
            throw new Exception("Invalid patient data");
        }else{
             Member m=(Member) param;
             checkValueConstraints(m);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete((Member)param);
    }
    
    private void checkValueConstraints(Member m) throws Exception {
        boolean exists= checkMemberHasSession(m);
        System.out.println(exists);
       if(exists){
            throw new Exception("Member with that ID has created training session and can not be deleted. Try again.");
       }
    }

    private boolean checkMemberHasSession(Member m) {
        try {
            List<TrainingSession> sessions = repository.getAll(new TrainingSession());
            for (TrainingSession session : sessions) {
                if(session.getMember().getId().equals(m.getId())){
                    return true;
                }
            }
        } catch (Exception ex) {
            
        }
        return false;
    }
    
}
