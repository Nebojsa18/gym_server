/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domain.Coach;
import domain.TrainingItem;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    T add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    List<T> getAll();
    List<T> getByQuery(T t,String query) throws Exception;
    
    T get(T t, String string) throws Exception;

    //public void addBoundObjects(TrainingItem trainingItem);

    
}
