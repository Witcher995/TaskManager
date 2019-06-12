/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Roles;
import com.utp.teamwork.repository.RolesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }
    
}
