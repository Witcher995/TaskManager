/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Comments;
import java.util.List;

/**
 *
 * @author AdamPrzedlacki
 */
public interface CommentsService {
    
    List <Comments> findCommentByTaskId(Long id);
    
}
