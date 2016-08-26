/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.dao;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.studentadm.model.Course;
import com.studentadm.model.Results;
import com.studentadm.model.Student;
import com.studentadm.service.CourseService;
import com.studentadm.service.StudentService;

/**
 *
 * @author Administrator
 * 
 */

@Repository
public class ResultDao implements ResultDaoInterface<Results> {
    
    
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}
    
    public ResultDao() {
    }
       
    //insert a new record
    @Override
    public void insert(Results entity) {
             
    	sessionFactory.getCurrentSession().saveOrUpdate(entity);  
              
    }

    // update a record
    @Override
    public void update(Results entity) {
        
    	sessionFactory.getCurrentSession().saveOrUpdate(entity);
        
    }

    // Select a record in result that have student_id = st_id and course_id = crs_id
    @SuppressWarnings("unchecked")
	@Override
    public Results selectById(int st_id, int crs_id) {
 
        String hql = "FROM Results "
                + "where pk.student.student_id = " + st_id + " and pk.course.course_id = " + crs_id ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Results> results = query.list();
        
        Results result = null;
        
        if (!(results.get(0)==null)){
            result = results.get(0) ;      
        }

        return result; 
    }

    // Select all results that have student_id = st_id
    @SuppressWarnings("unchecked")
	public List<Results> selectStudent(int st_id){
        
        String hql = "FROM Results "
                + "where pk.student.student_id = " + st_id  ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Results> results = query.list();
                
        return results;         
    }
    
    // Select all results that have course_id  = crs_id
    
    @SuppressWarnings("unchecked")
	public List<Results> selectCourse(int crs_id){
        
        String hql = "FROM Results "
                + "where pk.course.course_id = " + crs_id  ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Results> results = query.list();
                
        return results;         
    }
    
    //Delete a record result
    @Override
    public void delete(Results result) {        	
    	sessionFactory.getCurrentSession().delete(result);	
    }

    
    //Select all records of Results
    @SuppressWarnings("unchecked")
	@Override
    public List<Results> select() {
        
                
        List<Results> results = (List<Results>) sessionFactory.getCurrentSession().createQuery("from Results").list();
	
        return results;
    }
    

    // Write results table to a text file
    @SuppressWarnings("unchecked")
	@Override
    public void write_to_file(String filename) {
        
        //Create a file       
        File file = new File(filename);
        if (!file.exists()){
            File citydir = new File(file.getParent());
            if (!citydir.exists())
                citydir.mkdirs();
            
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }
        
        
        //Get information from Database
        String hql = "SELECT rlst FROM Results rlst";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Results> results = query.list();
        
        //write to file   
        PrintWriter fout = null;
        
        try {
            fout = new PrintWriter( new BufferedWriter (new FileWriter(file)));
        } catch (IOException ex) {
            Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        for(Results rlst:results){
            int student_id  = rlst.getStudent().getStudent_id();
            int course_id   = rlst.getCourse().getCourse_id();
            int mark1      = rlst.getMark1();
            int mark2      = rlst.getMark2();
         
            fout.println(student_id+","+course_id +","+mark1+","+mark2);               
        }
        fout.close();
    }

    //Import data from text file to Results table
    @Override
    public void insert_from_file(String filename) {
       File file = new File(filename);
       BufferedReader fin = null;
        
        if (!file.exists()){
            System.out.println("File not exists!");
        }else{
            System.out.println("Insert data from file ....");
            
            try {
                fin = new BufferedReader( new FileReader(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String line = null;
            try {
                line = fin.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
            while (line!=null){
                //slipt line data and add fields to Person object 
                                
                String[] components = line.split(",");
                                
                int student_id = Integer.parseInt(components[0]);
                int course_id = Integer.parseInt(components[1]);
                int mark1 = Integer.parseInt(components[2]);    
                int mark2 = Integer.parseInt(components[3]);
                System.out.println("Student " + student_id + " " + course_id + " "+ mark1 + " " +  mark2 );
                
                StudentService stService = new StudentService();
                CourseService crsService = new CourseService();

                Student st = stService.selectById(student_id);
                Course crs = crsService.selectById(course_id);
                
                 System.out.println("Student " + st);
                 
                  System.out.println(" Course " + crs);

                if ((st==null) || (crs==null))
                    System.out.println("Student or Course is null");
                else{

                    Results rsl = new Results(mark1,mark2);
                    rsl.setStudent(st);
                    rsl.setCourse(crs);

                    System.out.println("Result: " + rsl);
                //insert to database
                    this.insert(rsl);
                }
  
                
                 //read next line
                try {
                    line = fin.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
                }            
            }
            
            //close file
            try {                
                fin.close();
            } catch (IOException ex) {
                Logger.getLogger(ResultDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("end of inserting");
            
        }
    }

    
}
