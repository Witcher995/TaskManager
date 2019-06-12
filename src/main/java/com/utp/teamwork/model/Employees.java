/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author AdamPrzedlacki
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    @ManyToMany(cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "tasks_employees",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Tasks> task;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
    private List<Notifications> notification;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Roles role;

    public Employees(String username, String password, String firstName, String lastName, String email, Branch branch, Roles role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.branch = branch;
        this.role = role;
    }

}
