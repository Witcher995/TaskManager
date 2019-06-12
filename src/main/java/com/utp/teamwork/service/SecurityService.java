/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.service;

/**
 *
 * @author AdamPrzedlacki
 */
public interface SecurityService {
    
    String findLoggedInUsername();
    void autologin(String username, String password);
    
}
