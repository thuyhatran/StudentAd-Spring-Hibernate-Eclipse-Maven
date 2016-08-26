package com.studentadm.dao;

import java.util.List;

import com.studentadm.model.Student;
import com.studentadm.model.StudentsGrade;

public interface StudentDaoInterface {
	
	 public void insert(Student entity);
		
	    public void update(Student entity);
		
	    public Student selectById(int id);
		
	    public void delete(Student entity);
	    	
	    public List<Student> select();
	    
	    public int getNewStudentID();
	    
	    public List<StudentsGrade> getGrades();
	    public List<StudentsGrade> getTranscript(int student_id) ;
		
	    public void write_to_file(String filename);
	    public void insert_from_file(String filename);

}
