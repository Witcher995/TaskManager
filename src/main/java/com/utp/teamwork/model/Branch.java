/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String nameBranch;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="branch")
    private List<Employees> employee;

    public Branch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

}
