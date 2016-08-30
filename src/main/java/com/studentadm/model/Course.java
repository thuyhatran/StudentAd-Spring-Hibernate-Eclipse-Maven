/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Thuy Ha
 */
@SuppressWarnings("serial")
@Entity
@Table(name="COURSE1")
public class Course implements Serializable {
     @Id                      
    private int course_id;
     
    @Column(name="course_name", length=40) 
    private String course_name;

    //@OneToMany(mappedBy = "course")
   //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "pk.course")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.course")
  //  @Cascade({SAVE_UPDATE, DELETE, DELETE_ORPHAN})
    private Set<Results> results = new HashSet<Results>();
    
    public Course(int course_id, String course_name) {
        this.course_id = course_id;
        this.course_name = course_name;
    }

    public Course() {
    }

    public Course(int course_id) {
        this.course_id = course_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Set<Results> getResults() {
        return results;
    }

    public void setResults(Set<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Course{" + "course_id=" + course_id + ", course_name=" + course_name + '}';
    }

    
}
