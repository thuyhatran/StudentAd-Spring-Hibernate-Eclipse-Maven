package com.studentadm.dao;

import java.util.List;

import com.studentadm.model.Course;


public interface courseDaoInterface {
	
	 public void insert(Course entity);
		
	    public void update(Course entity);
		
	    public Course selectById(int id);
		
	    public void delete(Course entity);
	    	
	    public List<Course> select();
	    
	    public int getNewCourseID();
	    
		
	    public void write_to_file(String filename);
	    public void insert_from_file(String filename);

}
