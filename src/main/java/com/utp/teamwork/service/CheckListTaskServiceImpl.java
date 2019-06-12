/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Comments;
import com.utp.teamwork.repository.CheckListTaskRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */

@Service
public class CheckListTaskServiceImpl implements CheckListTaskService {
    
    @Autowired
    CheckListTaskRepository checkListTaskRepository;

    @Override
    public List<CheckListTask> findCheckListByTaskId(Long id) {

        List<CheckListTask> foundCheckList = new ArrayList<>();

        for (CheckListTask oneList : checkListTaskRepository.findAll()) {

            if (oneList.getTask().getId().equals(id)) {
                foundCheckList.add(oneList);
            }
        }

        return foundCheckList;

    }

}
