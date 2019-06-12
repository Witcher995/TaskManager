/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Notifications;
import java.util.List;

/**
 *
 * @author AdamPrzedlacki
 */
public interface NotificationsService {
    List<Notifications> findNotificationsByUsername(String username);
    void saveNotification(Employees employees, String communicat);
    void saveNotification(Employees employees, String communicat, String uri, boolean isChecked);

    public void deleteById(Long id);

   

   
    
}
