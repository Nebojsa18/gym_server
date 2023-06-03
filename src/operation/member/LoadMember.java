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
public class LoadMember extends AbstractGenericOperation{
    
    private Member member;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null || !(param instanceof Member)){
            throw new Exception("Invalid member data");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.member = null;
        
        List<Member>members = repository.getAll((Member)param);
        
        if(!members.isEmpty()){
            for (Member member : members) {
                if(member.equals((Member)param)){
                    this.member=member;
                }
            }
        }
    }

    public Member getMember() {
        return member;
    }
}
