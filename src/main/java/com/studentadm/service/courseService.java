/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentadm.dao.courseDao;
import com.studentadm.model.Course;

/**
 *
 * @author Administrator
 */

@Service
public class courseService implements courseServiceInterface{
    
    private static courseDao crsDao;

   public courseService() {
	        crsDao = new courseDao();
	    }
    
    public static courseDao getCrsDao() {
	return crsDao;
}


	@Override
    @Transactional
    public void insert(Course entity) {
        crsDao.insert(entity);
    }

 

	public static void setCrsDao(courseDao crsDao) {
		courseService.crsDao = crsDao;
	}



	@Override
    @Transactional
    public void update(Course entity) {
        
        crsDao.update(entity);
          
    }

    @Override
    @Transactional
    public Course selectById(int id) {
        
        Course st = (Course) crsDao.selectById(id);
        
        return st; 
    }

    @Override
    @Transactional
    public void delete(int id) {
        
		Course st = crsDao.selectById(id);
		crsDao.delete(st);
	
    }

    @Override
    @Transactional
    public List<Course> select() {
        
        List<Course> course = crsDao.select();
	
        return course;
    }
    
    @Override
    @Transactional
    public int getNewCourseID() {
               
        int curID = crsDao.getNewCourseID();
     
        return curID;
        
    }
    
    @Override
    @Transactional
    public void write_to_file(String filename)  {
        
        crsDao.write_to_file(filename);
        
    }

    @Override
    @Transactional
    public void insert_from_file(String filename) {
       
        crsDao.insert_from_file(filename);
     
    }     
    
}
