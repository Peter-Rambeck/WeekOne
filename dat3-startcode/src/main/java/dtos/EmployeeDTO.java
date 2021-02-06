/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class EmployeeDTO {
    
    private int id;
    private String name;
    private String address;
    
    public EmployeeDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public static List<EmployeeDTO> getDtos(List<Employee> es){
        List<EmployeeDTO> edtos = new ArrayList();
        es.forEach(e->edtos.add(new EmployeeDTO(e)));
        return edtos;
    }
    
    public EmployeeDTO(Employee e) {
        this.id = e.getId();
        this.name = e.getName();
        this.address = e.getAddress();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
