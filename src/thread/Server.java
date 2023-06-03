/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import constants.ServerConstants;
import controller.Controller;
import domain.Coach;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Server extends Thread{
    
    private ServerSocket serverSocket;
    private List<ProcessRequests> clients;

    public Server() throws IOException {
        Properties properties= new Properties();
        properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
        String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
        serverSocket = new ServerSocket(Integer.parseInt(port));
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while(!serverSocket.isClosed()){
                System.out.println("Cekam klijente");
                Socket s= serverSocket.accept();
                System.out.println("Klijent se povezao");

                //connectClient(s); zavrsi ovo za klijente
                ProcessRequests pr = new ProcessRequests(this,s);
                clients.add(pr);
                pr.start();
            }
        } catch (Exception e) {
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public void stopServer() throws IOException{
        for (ProcessRequests client : clients) {
            client.stopThread();
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Communication has been interrupted.");
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverSocket.close();
    }
    
    public List<Coach> getActiveUsers(){
        List<Coach> coaches = new ArrayList();
        for (ProcessRequests pr : clients) {
            if(pr.getCoach()!=null){
                coaches.add(pr.getCoach());
            }
        }
        return coaches;
    }
    
    void logout(ProcessRequests pr) throws IOException{
        pr.getSocket().close();
        clients.remove(pr);
        System.out.println("Client thread removed.");
    }

    public void closeActiveUsers() {
        for (ProcessRequests pr : clients) {
            try {
                Controller.getActiveUsers().remove(pr.getCoach());
                logout(pr);
//                pr.getSocket().close();
//                clients.remove(pr);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

   
    
}
