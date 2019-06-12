/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Branch;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Roles;
import com.utp.teamwork.repository.BranchRepository;
import com.utp.teamwork.repository.EmployeesRepository;
import com.utp.teamwork.repository.RolesRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    BranchRepository branchRepository;
    
    @Autowired RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(Employees employee, Long role, Long branchId) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setBranch(branchRepository.findOne(branchId));
        employee.setRole(rolesRepository.findOne(role));
        employeesRepository.saveAndFlush(employee);
    }

    @Override
    public void updateUser(Employees employee, String role, Long branchId) {
        employee.setBranch(branchRepository.findOne(branchId));
        employee.setRole(new Roles(role));
        employeesRepository.saveAndFlush(employee);
    }

    @Override
    public Employees findByUsername(String username) {
        return employeesRepository.findByUsername(username);
    }

    @Override
    public List<Employees> findAll() {
        return employeesRepository.findAll();
    }

    @Override
    public void deleteUserById(Long employeeId) {
        employeesRepository.delete(employeeId);
    }

    @Override
    public Employees findUserById(Long employeeId) {
        return employeesRepository.findOne(employeeId);
    }

    @Override
    public void updateUser(Employees employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeesRepository.saveAndFlush(employee);
    }

}
