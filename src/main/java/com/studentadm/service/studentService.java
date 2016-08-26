/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studentadm.dao.StudentDao;
import com.studentadm.model.Student;
import com.studentadm.model.StudentsGrade;

/**
 *
 * @author Administrator
 */
@Service
public class StudentService implements StudentServiceInterface{
    
    private static StudentDao stDao;

    public StudentService() {
        stDao = new StudentDao();
    }
    
    
    
    public static StudentDao getStDao() {
		return stDao;
	}



	public static void setStDao(StudentDao stDao) {
		StudentService.stDao = stDao;
	}



	@Override
    @Transactional
    public void insert(Student entity) {

        stDao.insert(entity);

    }

    @Override
    @Transactional
    public void update(Student entity) {

        stDao.update(entity);

    }

    @Override
    @Transactional
    public Student selectById(int id) {

        Student st = stDao.selectById(id);

        return st; 
    }

    @Override
    @Transactional
    public void delete(int id) {

	Student st = stDao.selectById(id);
	stDao.delete(st);

    }

    @Override
    @Transactional
    public List<Student> select() {

        List<Student> students = stDao.select();

        return students;
    }
    
     @Override
     @Transactional
    public int getNewStudentID() {
        
        int curID = stDao.getNewStudentID();
        
        return curID;    
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

    @Override
    @Transactional
    public List<StudentsGrade> getGrades() {

        List<StudentsGrade> student_Grades = stDao.getGrades();

        return student_Grades;
    }

    @Override
    @Transactional
    public List<StudentsGrade> getTranscript(int student_id) {

        List<StudentsGrade> student_Grades = stDao.getTranscript(student_id);

        return student_Grades;
    }
         
}
