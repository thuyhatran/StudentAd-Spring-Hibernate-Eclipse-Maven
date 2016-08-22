/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentadm.dao.resultDao;
import com.studentadm.model.Results;

/**
 *
 * @author thuyha
 */

@Service("resultService")
public class resultService implements resultServiceInterface{
    
    private static resultDao stDao;
    
    public resultService() {
        stDao = new resultDao();
    }
        

    @Override
    @Transactional
    public void insert(Results entity) {
        
        stDao.insert(entity);
        
    }

    @Override
    @Transactional
    public void update(Results entity) {
        
        stDao.update(entity);
       
    }

    @Override
    @Transactional
    public Results selectById(int st_id, int crs_id) {
       
        Results st = stDao.selectById(st_id, crs_id);
       
        return st; 
    }

    @Override
    @Transactional
    public void delete(int st_id, int crs_id) {
	         
		Results st = stDao.selectById(st_id, crs_id);
		stDao.delete(st);

    }

    @Override
    @Transactional
    public List<Results> select() {
        
        List<Results> results = stDao.select();
        
        return results;
    }

    @Override
    @Transactional
    public void deleteStudent(int st_id) {
        
        List<Results> results = stDao.selectStudent(st_id);
        for (Results result: results ){
            System.out.println(result);
            stDao.delete(result);
        }
	      
        System.out.println("Delle all recourd of  student id=" + st_id);
    }

    @Override
    @Transactional
    public void deleteCourse(int course_id) {
           
        List<Results> results = stDao.selectCourse(course_id);
        for (Results result: results ){
            System.out.println(result);
            stDao.delete(result);
        }
        
    }
    
    @Override
    @Transactional
    public void write_to_file(String filename)  {

        stDao.write_to_file(filename); 
       
    }

    @Override
    @Transactional
    public void insert_from_file(String filename) {

        stDao.insert_from_file(filename);
     
    } 
    
    
    
}
