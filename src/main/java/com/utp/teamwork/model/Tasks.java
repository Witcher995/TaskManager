/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
public class Tasks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String priority;
    @NotNull
    private String status;

    public Tasks(String title) {
        this.title = title;
    }

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "tasks_employees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employees> employees;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="tasks")
    private List <Comments> comments;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="task")
    private List <CheckListTask> checkListTasks;

    /*private String status;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateStart;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateStop;
    
    private String progress; /*
    
    

    
    
    /*@OneToMany
    @JoinColumn(name = "task_id")
    private List<Comments> comments;*/
}
