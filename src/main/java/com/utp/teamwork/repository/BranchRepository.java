/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.repository;

import com.utp.teamwork.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author AdamPrzedlacki
 */
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByNameBranch(String nameBranch);
}
