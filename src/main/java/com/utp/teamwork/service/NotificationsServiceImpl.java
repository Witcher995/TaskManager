/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Notifications;
import com.utp.teamwork.model.Tasks;
import com.utp.teamwork.repository.NotificationsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AdamPrzedlacki
 */
@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    NotificationsRepository notificationsRepository;

    @Override
    public List<Notifications> findNotificationsByUsername(String username) {
        List<Notifications> notifications = notificationsRepository.findAll();
        List<Notifications> notificationUsername = new ArrayList<>();
        for (Notifications notification : notifications) {
            if (notification.getEmployee().getUsername().equals(username)) {
                notificationUsername.add(notification);
            }
        }
        return notificationUsername;
    }

    @Override
    public void saveNotification(Employees employees, String communicat, String uri, boolean isChecked) {
        Notifications notification = new Notifications(employees, communicat, uri, false);
        notificationsRepository.saveAndFlush(notification);
    }

    @Override
    public void saveNotification(Employees employees, String communicat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        notificationsRepository.delete(id);
    }

}
