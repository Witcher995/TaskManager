/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import lombok.*;

/**
 *
 * @author AdamPrzedlacki
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfComment;

    @NotNull
    @JoinColumn(name = "EmployeeId")
    @ManyToOne()
    private Employees employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    Tasks tasks;
}
