/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Tasks;
import com.utp.teamwork.repository.EmployeesRepository;
import com.utp.teamwork.repository.TasksRepository;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */
@Service
public class TasksServiceImpl implements TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeesRepository employeesRepository;

    @Override
    public List<Tasks> findAll() {
        return tasksRepository.findAll();
    }

    @Override
    public Tasks findUserById(Long taskId) {
        return tasksRepository.findOne(taskId);
    }

    @Override
    public void saveTask(Tasks task, Long employeeId) {
        List<Employees> employee = new ArrayList<>();
        employee.add(employeeService.findUserById(employeeId));
        task.setEmployees(employee);
        tasksRepository.saveAndFlush(task);
    }

    @Override
    public List<Tasks> findByUserName(String username) {
        List<Tasks> tasks = tasksRepository.findAll();
        List<Tasks> tasksWithLoggedUsername = new ArrayList<>();
        for (Tasks task : tasks) {
            for (Employees employee : task.getEmployees()) {
                if (employee.getUsername().equals(username)) {
                    tasksWithLoggedUsername.add(task);
                }
            }
        }
        System.out.println(username);
        return tasksWithLoggedUsername;
    }

    @Override
    public void updateTask(Tasks task) {
        tasksRepository.saveAndFlush(task);
    }

    @Override
    public void removeUser(Long taskId, Long employeeId) {
        Tasks tasks = tasksRepository.findOne(taskId);
        List<Employees> emp = tasks.getEmployees();
        for (Employees employee : emp) {
            if (employee.equals(employeeService.findUserById(employeeId))) {
                emp.remove(employee);
            }
        }

        tasks.setEmployees(emp);
        tasksRepository.saveAndFlush(tasks);
    }

    @Override
    public List<Tasks> findByStatus(String done) {
        List<Tasks> allTask = tasksRepository.findAll();
        List<Tasks> doneTask = new ArrayList<>();
        for (Tasks task : allTask) {
            if (task.getStatus().equals(done)) {
                doneTask.add(task);
            }
        }
        return doneTask;
    }

    @Override
    public void deleteById(Long taskId) {
        Tasks task = tasksRepository.findOne(taskId);
        List <Employees> deleteEmployees = new ArrayList<>();
        task.setEmployees(deleteEmployees);
        tasksRepository.delete(task);
    }

}
