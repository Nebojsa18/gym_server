/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.member;

import domain.Member;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Admin
 */
public class AddMember extends AbstractGenericOperation{

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
        repository.add((Member)param);
    }

    private void checkValueConstraints(Member m) throws Exception {
        boolean exists= checkIfExists(m);
       if(exists){
            throw new Exception("Member with that ID already exists. Try again.");
       }
    }

    private boolean checkIfExists(Member member) throws Exception {
        List<Member> members= repository.getByQuery(new Member(), "");
        
        for(Member m: members){
            if(m!=null){
                if(m.equalsWithoutID(member)){
                    return true;
                }
            }
        } 
        return false;
    }
    
}
