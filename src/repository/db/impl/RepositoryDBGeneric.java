/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.AbstractDO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;



public class RepositoryDBGeneric implements DBRepository<AbstractDO>{

    @Override
    public List<AbstractDO> getAll(AbstractDO param) throws Exception {
        List<AbstractDO> abstractDOs= new ArrayList<>();
        Connection connection = DBConnectionFactory.getInstance().getConnection();
        Statement statement = connection.createStatement();
        String query = param.getStatementSelectAllQuery();
        ResultSet rs =statement.executeQuery(query);
        while(rs.next()){
                abstractDOs.add(param.getEntityFromResultSet(rs));
            }
        return abstractDOs;
    }

    @Override
    public AbstractDO add(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(param.getClassName())
                    .append(" (")
                    .append(param.getAttributeList())
                    .append(")")
                    .append(" VALUES (")
                    .append(param.getAttributeValues())
                    .append(")");
            String query = sb.toString();

            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                param.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return param; //vraca objekat sa generisanim id iz baze
    }

    @Override
    public void edit(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(param.getClassName())
                    .append(" SET ")
                    .append(param.setAttributeValues())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
                
            
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(AbstractDO param) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(param.getClassName())
                    .append(" WHERE ")
                    .append(param.getQueryCondition());
            String query = sb.toString();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<AbstractDO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    


    @Override
    public List<AbstractDO> getByQuery(AbstractDO t, String query) throws Exception {
        List<AbstractDO> abstractDOs= new ArrayList<>();
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            System.out.println(t.getStatementSelectAllQuery()+query);
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+query);
            while(resultSet.next()){
                abstractDOs.add(t.getEntityFromResultSet(resultSet));
            }
        
            return abstractDOs;
    }

    @Override
    public AbstractDO get(AbstractDO t, String string) throws Exception {
        AbstractDO abstractDO=null;
            Connection connection= DBConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(t.getStatementSelectAllQuery()+string);
            while(resultSet.next()){
                abstractDO=t.getEntityFromResultSet(resultSet);
            }
        
            return abstractDO;
    }

//    @Override
//    public void addBoundObjects(TrainingItem trainingItem) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    
}
