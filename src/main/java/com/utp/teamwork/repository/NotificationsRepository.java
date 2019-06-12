/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.repository;

import com.utp.teamwork.model.Notifications;
import com.utp.teamwork.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author AdamPrzedlacki
 */
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    
}
