/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Employees;
import java.util.List;

/**
 *
 * @author AdamPrzedlacki
 */
public interface EmployeeService {
    
  void saveUser(Employees employee,Long role, Long branchId);
  void updateUser(Employees employee, String role, Long branchId);
  void deleteUserById(Long employeeId);
  Employees findUserById(Long employeeId);
  Employees findByUsername(String username);
  List<Employees> findAll();

    public void updateUser(Employees employee);
  
}
