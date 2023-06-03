/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Coach;
import domain.Excercise;
import domain.Member;
import domain.TrainingSession;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.coach.AddCoach;
import operation.coach.EditCoach;
import operation.coach.GetAllCoaches;
import operation.coach.SearchCoaches;
import operation.coach.LoadCoach;
import operation.excercise.GetAllExcercises;
import operation.member.AddMember;
import operation.member.DeleteMember;
import operation.member.EditMember;
import operation.member.GetAllMembers;
import operation.member.SearchMembers;
import operation.member.LoadMember;
import operation.trainingsession.AddTrainingSession;
import operation.trainingsession.DeleteTrainingSession;
import operation.trainingsession.EditTrainingSession;
import operation.trainingsession.GetAllTrainingSessions;
import operation.trainingsession.SearchTrainingSessions;
import operation.trainingsession.LoadTrainingSession;



/**
 *
 * @author Admin
 */
public class Controller {

    
    private static Controller controller;
    
    private static List<Coach> activeUsers;
    public Controller() {
        activeUsers= new ArrayList<>();
    }
    
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    

    public static List<Coach> getActiveUsers() {
        return activeUsers;
    }
    
    
    
    public Coach login(String username, String password) throws Exception { //uradjeno
        
        AbstractGenericOperation operation = new GetAllCoaches();
        operation.execute(new Coach());
        List<Coach> coaches = ((GetAllCoaches)operation).getCoaches();
        for (Coach coach : coaches) {
            if(coach.getUsername().equals(username)&& coach.getPassword().equals(password)){
                if(activeUsers.contains(coach)){
                    throw new Exception("User already logged in!");
                }else{
                    activeUsers.add(coach);
                    return coach;
                }
                
            }
        }
        throw new Exception("Unknown user!");
    }
    
    
    public void logout(Coach coach) {
        activeUsers.remove(coach);
    }
    
    public List<Member> getAllMembers() throws Exception {  //uradjenoo
        AbstractGenericOperation operation = new GetAllMembers();
        operation.execute(new Member());
        return ((GetAllMembers)operation).getMembers();
    }
    
    public void addMember(Member m) throws Exception{ // uradjenoo
        //repositoryMember.add(member);
        AbstractGenericOperation operation = new AddMember();
        operation.execute(m);
    }
    
    public Member loadMember(Member m) throws Exception{
        AbstractGenericOperation operation = new LoadMember();
        operation.execute(m);
        return((LoadMember)operation).getMember();
    }
    
    public void editMember(Member m) throws Exception{  //URADJENO
        //repositoryMember.edit(member);
        AbstractGenericOperation operation = new EditMember();
        operation.execute(m);
    }
    
    public List<Coach> getAllCoaches() throws Exception{  //URADJENO
        //return repositoryCoach.getAll();
        AbstractGenericOperation operation = new GetAllCoaches();
        operation.execute(new Coach());
        return ((GetAllCoaches)operation).getCoaches();
    }
    
    public Coach loadCoach(Coach c) throws Exception{
        AbstractGenericOperation operation = new LoadCoach();
        operation.execute(c);
        return ((LoadCoach)operation).getCoach();
    }
    
     public void addCoach(Coach c) throws Exception{ //URADJENO
        //repositoryCoach.add(coach);
        AbstractGenericOperation operation = new AddCoach();
        operation.execute(c);
    }
    
    public void updateCoach(Coach c) throws Exception{  //URADJENO
        //repositoryCoach.edit(coach);
        AbstractGenericOperation operation = new EditCoach();
        operation.execute(c);
    }
    
    public List<Excercise> getAllExcercises() throws Exception{
//        return repositoryExcercise.getAll();
        AbstractGenericOperation operation = new GetAllExcercises();
        operation.execute(new Excercise());
        return ((GetAllExcercises)operation).getExcercises();
    }
    
    public List<TrainingSession> getAllTrainingSessions() throws Exception{
        AbstractGenericOperation operation = new GetAllTrainingSessions();
        operation.execute(new TrainingSession());
        return ((GetAllTrainingSessions)operation).getSessions();
        //return repositoryTrainingSession.getAll();
    }
    
    public TrainingSession loadTrainingSession(TrainingSession ts) throws Exception{
        AbstractGenericOperation operation = new LoadTrainingSession();
        operation.execute(ts);
        return ((LoadTrainingSession)operation).getTrainingSession();
    }
    
    public void deleteMember(Member m) throws Exception{
        AbstractGenericOperation operation = new DeleteMember();
        operation.execute(m);
    }
    
    
    public void addTrainingSession(TrainingSession ts) throws Exception{
         AbstractGenericOperation operation = new AddTrainingSession();
         operation.execute(ts);
    } 
    
    public void deleteTrainingSession(TrainingSession ts) throws Exception{
        AbstractGenericOperation operation = new DeleteTrainingSession();
        operation.execute(ts);
        
    }

    public List<Member> searchMembers(String query) throws Exception {
        AbstractGenericOperation operation = new SearchMembers();
        operation.execute(query);
        return ((SearchMembers)operation).getMembers();
    }
    
    public List<Coach> searchCoaches(String query) throws Exception{
        AbstractGenericOperation operation = new SearchCoaches();
        operation.execute(query);
        return ((SearchCoaches)operation).getCoaches();
    }
    
    public void editTrainingSession(TrainingSession ts) throws Exception{
        AbstractGenericOperation operation = new EditTrainingSession();
        operation.execute(ts);
    }

    public List<TrainingSession> searchTrainingSessions(String query) throws Exception {
        AbstractGenericOperation operation = new SearchTrainingSessions();
        operation.execute(query);
        return ((SearchTrainingSessions)operation).getSessions();
    }

    
    
    
    
}
