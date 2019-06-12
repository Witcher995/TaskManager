/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.controller;

import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Tasks;
import com.utp.teamwork.repository.CheckListTaskRepository;
import com.utp.teamwork.repository.TasksRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AdamPrzedlacki
 */
@RestController
public class ApiController {

    @Autowired
    private TasksRepository tasksRepository;

    @RequestMapping(path = "/api/update/task/{id}/{status}", method = RequestMethod.POST)
    String updateTask(@PathVariable("id") Long id, @PathVariable("status") String status) {
        Tasks task = tasksRepository.findOne(id);
        System.out.println(status);
        task.setStatus(status);
        tasksRepository.saveAndFlush(task);
        return "done";
    }
}
