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

@Service
public class resultService implements resultServiceInterface{
    
    private static resultDao rslDao;
    
    public resultService() {
        rslDao = new resultDao();
    }
        
    
    

    public static resultDao getRslDao() {
		return rslDao;
	}




	public static void setRslDao(resultDao rslDao) {
		resultService.rslDao = rslDao;
	}




	@Override
    @Transactional
    public void insert(Results entity) {
        
        rslDao.insert(entity);
        
    }

    @Override
    @Transactional
    public void update(Results entity) {
        
        rslDao.update(entity);
       
    }

    @Override
    @Transactional
    public Results selectById(int st_id, int crs_id) {
       
        Results st = rslDao.selectById(st_id, crs_id);
       
        return st; 
    }

    @Override
    @Transactional
    public void delete(int st_id, int crs_id) {
	         
		Results st = rslDao.selectById(st_id, crs_id);
		rslDao.delete(st);

    }

    @Override
    @Transactional
    public List<Results> select() {
        
        List<Results> results = rslDao.select();
        
        return results;
    }

    @Override
    @Transactional
    public void deleteStudent(int st_id) {
        
        List<Results> results = rslDao.selectStudent(st_id);
        for (Results result: results ){
            System.out.println(result);
            rslDao.delete(result);
        }
	      
        System.out.println("Delle all recourd of  student id=" + st_id);
    }

    @Override
    @Transactional
    public void deleteCourse(int course_id) {
           
        List<Results> results = rslDao.selectCourse(course_id);
        for (Results result: results ){
            System.out.println(result);
            rslDao.delete(result);
        }
        
    }
    
    @Override
    @Transactional
    public void write_to_file(String filename)  {

        rslDao.write_to_file(filename); 
       
    }

    @Override
    @Transactional
    public void insert_from_file(String filename) {

        rslDao.insert_from_file(filename);
     
    } 
    
    
    
}
