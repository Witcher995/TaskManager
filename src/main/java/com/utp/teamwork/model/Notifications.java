/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.model;

import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author AdamPrzedlacki
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employees employee;

    @NotNull
    private String communicat;

    @NotNull
    private String uri;

    @NotNull
    private boolean isRead;

    public Notifications(Employees employee, String communicat, String uri, boolean isRead) {
        this.employee = employee;
        this.communicat = communicat;
        this.uri = uri;
        this.isRead = isRead;
    }
}
