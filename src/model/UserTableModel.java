/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Coach;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class UserTableModel extends AbstractTableModel{
   
    String[] columnNames = new String[]{"username","firstname","lastname"};
    List<Coach> coaches;

    public UserTableModel() {
        coaches = new ArrayList<>();
    }

    public UserTableModel(List<Coach> coaches) {
        this.coaches = coaches;
    }
    
    
    @Override
    public String getColumnName(int column) {
    if(column>columnNames.length) return "n/a";
    return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return coaches.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coach c = coaches.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getUsername();
            case 1: return c.getFirstname();
            case 2: return c.getLastname();
            default: return "n/a";
        }
    }
    
    public void addUser(Coach c){
        if(coaches.contains(c)) return;
        coaches.add(c);
        fireTableDataChanged();
    }
}
