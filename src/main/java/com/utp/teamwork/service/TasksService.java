/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Tasks;
import java.util.List;

/**
 *
 * @author AdamPrzedlacki
 */
public interface TasksService {
    
    void saveTask(Tasks task, Long employeeId);
    void updateTask(Tasks task);
    Tasks findUserById(Long taskId);
    List<Tasks> findByUserName(String username);
    List<Tasks> findAll();

    public void removeUser(Long taskId, Long employeeId);

    List<Tasks> findByStatus(String done);

    public void deleteById(Long taskId);
    
}
