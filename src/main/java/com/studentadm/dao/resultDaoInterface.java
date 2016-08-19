package com.studentadm.dao;

import java.util.List;

public interface resultDaoInterface<T> {
	   public void insert(T entity);
		
	    public void update(T entity);
		
	    public T selectById(int st_id, int crs_id);
		
	    public void delete(T entity);
	    	
	    public List<T> select();
	    
	    public void write_to_file(String filename);
	    public void insert_from_file(String filename);

}
