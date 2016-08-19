/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;


import java.util.List;

import com.studentadm.dao.studentDao;
import com.studentadm.model.Student;
import com.studentadm.model.StudentsGrade;

/**
 *
 * @author Administrator
 */
public class studentService implements studentServiceInterface{
    
    private static studentDao stDao;

    public studentService() {
        stDao = new studentDao();
    }
    
  @Override
    public void insert(Student entity) {
        stDao.openCurrentSessionwithTransaction();
        stDao.insert(entity);
        stDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(Student entity) {
        stDao.openCurrentSessionwithTransaction();
        stDao.update(entity);
        stDao.closeCurrentSessionwithTransaction();  
    }

    @Override
    public Student selectById(int id) {
        stDao.openCurrentSession();
        Student st = stDao.selectById(id);
        stDao.closeCurrentSession();
        return st; 
    }

    @Override
    public void delete(int id) {
        stDao.openCurrentSessionwithTransaction();
	Student st = stDao.selectById(id);
	stDao.delete(st);
	stDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public List<Student> select() {
        stDao.openCurrentSession();
        List<Student> students = stDao.select();
	stDao.closeCurrentSession();
        return students;
    }
    
     @Override
    public int getNewStudentID() {
        
        stDao.openCurrentSession();
        int curID = stDao.getNewStudentID();
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

    @Override
    public List<StudentsGrade> getGrades() {
        stDao.openCurrentSession();
        List<StudentsGrade> student_Grades = stDao.getGrades();
        stDao.closeCurrentSession(); 
        return student_Grades;
    }

    @Override
    public List<StudentsGrade> getTranscript(int student_id) {
        stDao.openCurrentSession();
        List<StudentsGrade> student_Grades = stDao.getTranscript(student_id);
        stDao.closeCurrentSession(); 
        return student_Grades;
    }
         
}
