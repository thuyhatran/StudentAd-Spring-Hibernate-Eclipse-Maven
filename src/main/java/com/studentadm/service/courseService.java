/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;


import java.util.List;

import com.studentadm.dao.courseDao;
import com.studentadm.model.Course;

/**
 *
 * @author Administrator
 */
public class courseService implements courseServiceInterface{
    
    private static courseDao stDao;

    public courseService() {
        stDao = new courseDao();
    }
    
  @Override
    public void insert(Course entity) {
        stDao.openCurrentSessionwithTransaction();
        stDao.insert(entity);
        stDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Course entity) {
        stDao.openCurrentSessionwithTransaction();
        stDao.update(entity);
        stDao.closeCurrentSessionwithTransaction();  
    }

    @Override
    public Course selectById(int id) {
        stDao.openCurrentSession();
        Course st = (Course) stDao.selectById(id);
        stDao.closeCurrentSession();
        return st; 
    }

    @Override
    public void delete(int id) {
        stDao.openCurrentSessionwithTransaction();
	Course st = stDao.selectById(id);
	stDao.delete(st);
	stDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public List<Course> select() {
        stDao.openCurrentSession();
        List<Course> course = stDao.select();
	stDao.closeCurrentSession();
        return course;
    }
    
       @Override
    public int getNewCourseID() {
        
        stDao.openCurrentSession();
        int curID = stDao.getNewCourseID();
        stDao.closeCurrentSession();
        
        return curID;
        
    }
    
    @Override
    public void write_to_file(String filename)  {
        stDao.openCurrentSession();
        stDao.write_to_file(filename);
        stDao.closeCurrentSession();  
        
       
    }

    @Override
    public void insert_from_file(String filename) {
        stDao.openCurrentSessionwithTransaction();
        stDao.insert_from_file(filename);
        stDao.closeCurrentSessionwithTransaction(); 
     
    } 
    
    
}
