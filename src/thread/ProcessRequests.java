/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Coach;
import domain.Member;
import domain.TrainingSession;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProcessRequests extends Thread{
    Socket socket;
    Sender sender;
    Receiver receiver;
    Server server;
    Member member;
    Coach coach;
    TrainingSession trainingSession;
    //

    public ProcessRequests(Server server, Socket socket) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        
        while(!socket.isClosed()){
            try {
                Request request = (Request) receiver.receive();
                Response response= new Response();
                try{
                    
                    switch (request.getOperation()) {
                        case LOGIN:
                            coach = (Coach) request.getObject();
                            coach = Controller.getInstance().login(coach.getUsername(),coach.getPassword());
                            response.setResult(coach);
                            break;
                        case LOG_OUT:
                            Controller.getInstance().logout(coach);
                            server.logout(this);
                            break;
                        case ADD_MEMBER:
                            member = (Member) request.getObject();
                            Controller.getInstance().addMember(member);
                            response.setResult(member);
                            break;
                        case LOAD_MEMBER:
                            member = (Member) request.getObject();
                            response.setResult(Controller.getInstance().loadMember(member));
                            break;
                        case EDIT_MEMBER:
                            member = (Member) request.getObject();
                            Controller.getInstance().editMember(member);
                            System.out.println(member);
                            break;
                            
                        case ADD_COACH:
                            coach = (Coach) request.getObject();
                            Controller.getInstance().addCoach(coach);
                            break;
                        
                        case EDIT_COACH:
                            coach = (Coach) request.getObject();
                            Controller.getInstance().updateCoach(coach);
                            break;
                        case LOAD_COACH:
                            coach = (Coach) request.getObject();
                            response.setResult(Controller.getInstance().loadCoach(coach));
                            break;
                        case GET_ALL_MEMBERS:
                            response.setResult(Controller.getInstance().getAllMembers());
                            break;
                        case SEARCH_MEMBERS:
                            String query = (String) request.getObject();
                            response.setResult(Controller.getInstance().searchMembers(query));
                            break;        
                        case GET_ALL_COACHES:
                            response.setResult(Controller.getInstance().getAllCoaches());
                            break;
                        case SEARCH_COACHES:
                            String query1 = (String) request.getObject();
                            response.setResult(Controller.getInstance().searchCoaches(query1));
                            break;
                        case GET_ALL_EXCERCISES:
                            response.setResult(Controller.getInstance().getAllExcercises());
                            break;
                        case GET_ALL_TRAINING_SESSIONS:
                            response.setResult(Controller.getInstance().getAllTrainingSessions());
                            break;
                        case LOAD_TRAINING_SESSION:
                            trainingSession = (TrainingSession) request.getObject();
                            response.setResult(Controller.getInstance().loadTrainingSession(trainingSession));
                            break;
                        case DELETE_MEMBER:
                            member = (Member) request.getObject();
                            Controller.getInstance().deleteMember(member);
                            break;
                        case ADD_TRAINING_SESSION:
                            trainingSession = (TrainingSession) request.getObject();
                            Controller.getInstance().addTrainingSession(trainingSession);
                            break;
                        case DELETE_TRAINING_SESSION:
                            trainingSession = (TrainingSession) request.getObject();
                            Controller.getInstance().deleteTrainingSession(trainingSession);
                            break;
                        case GET_TRAINING_SESSION:
                            trainingSession = (TrainingSession) request.getObject();
                            System.out.println(trainingSession);
                            List<TrainingSession> lts = Controller.getInstance().getAllTrainingSessions();
                            for (TrainingSession lt : lts) {
                                if(trainingSession.getId().equals(lt.getId())){
                                    response.setResult(lt);
                                }
                            }
                            break;
                        case EDIT_TRAINING_SESSION:
                            trainingSession = (TrainingSession) request.getObject();
                            Controller.getInstance().editTrainingSession(trainingSession);
                            break;
                        case SEARCH_TRAINING_SESSIONS:
                            String query2 = (String) request.getObject();
                            response.setResult(Controller.getInstance().searchTrainingSessions(query2));
                            break;    
                    }
                }catch (Exception ex) {
                    //ex.printStackTrace();
                    response.setException(ex);
                }
                sender.send(response);
            } catch (Exception ex) {
                System.out.println("Client has disconnected");
            }
        }
        
    }

    public Socket getSocket() {
        return socket;
    }

    public Coach getCoach() {
        return coach;
    }
    
    

    void stopThread() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error stopping communication - ProcessRequests");
        }
    }
    
    
    
    
}
