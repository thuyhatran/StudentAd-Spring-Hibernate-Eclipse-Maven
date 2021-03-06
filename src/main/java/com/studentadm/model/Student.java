package com.studentadm.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


/**
*
* @author Thuy Ha
* 
*/


@SuppressWarnings("serial")
@Entity
@Table(name="STUDENT")
public class Student implements Serializable {
    @Id                      
    private int student_id;
    
    @Size(min=1, max=40) // Spring MVC Form validation .jar file requried
    @Column(name="first_name", length=40)
    private String first_name;
    
    @NotEmpty //Hibernate validation - .jar file required
    @Column(name="last_name", length=40)
    private String last_name;
    
    @Column(name="gender", length=1)
    private String gender;
    
   
    @Column(name="start_date")
    private Date start_date;
    
    @Email  //Hibernate validation
    @Column(name="email", length=40)
    private String email;
    
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pk.student")
    @OneToMany(fetch = FetchType.EAGER,  mappedBy = "pk.student")
 //   @Cascade({SAVE_UPDATE, DELETE, DELETE_ORPHAN})
   // @Cascade({SAVE_UPDATE, DELETE})
   // @Fetch(FetchMode.SELECT)
    private Set<Results> results = new HashSet<Results>();;

    
   //contructors


    public Student() {
       
    }

    public Student(int student_id, String first_name, String last_name, String gender, Date start_date, String email,
		Set<Results> results) {
	super();
	this.student_id = student_id;
	this.first_name = first_name;
	this.last_name = last_name;
	this.gender = gender;
	this.start_date = start_date;
	this.email = email;
	this.results = results;
}
    
    

	public Student(String first_name, String last_name, String gender, Date start_date, String email) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.start_date = start_date;
		this.email = email;
	}

	public Student(int student_id, String first_name, String last_name, String gender, Date start_date, String email) {
		super();
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.start_date = start_date;
		this.email = email;
	}

	public Student(int student_id) {
      
        this.student_id = student_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public Set<Results> getResults() {
        return results;
    }

    public void setResults(Set<Results> results) {
        this.results = results;
    }

    
    @Override
    public String toString() {
        return "Student{" + "student_id=" + student_id + ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", start_date=" + start_date + ", email=" + email + '}';
    }


    
}

