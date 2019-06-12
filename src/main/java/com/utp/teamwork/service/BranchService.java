/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Branch;
import java.util.List;

/**
 *
 * @author AdamPrzedlacki
 */
public interface BranchService {
    
    void saveBranch(Branch branch);
    List<Branch> findAll();
    
}
