/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Branch;
import com.utp.teamwork.repository.BranchRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */
@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    BranchRepository branchRepository;
    
    @Override
    public void saveBranch(Branch branch) {
        branchRepository.saveAndFlush(branch);
    }

    @Override
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    
}
