/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Comments;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.repository.CommentsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public List<Comments> findCommentByTaskId(Long Id) {

        List<Comments> foundComments = new ArrayList<>();

        for (Comments comment : commentsRepository.findAll()) {

            if (comment.getTasks().getId().equals(Id)) {
                foundComments.add(comment);
            }
        }

        return foundComments;
    }
}
