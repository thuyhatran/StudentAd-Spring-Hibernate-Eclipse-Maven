/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.service;

import com.studentadm.model.Results;
import com.studentadm.model.Results_view;
import com.studentadm.dao.ResultDao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author thuyha
 */
@Service
public class ResultViewService implements ResultViewServiceInterface{
    
    private static ResultDao rslDao;
    
    public ResultViewService() {
    	rslDao = new ResultDao();
    }
    
    
    
        
    public static ResultDao getRslDao() {
		return rslDao;
	}




	public static void setRslDao(ResultDao rslDao) {
		ResultViewService.rslDao = rslDao;
	}




	@Override
    @Transactional
    public Results_view selectById(int st_id, int crs_id) {

        Results st = rslDao.selectById(st_id, crs_id);

        Results_view rls_view = new Results_view(st);
        return rls_view; 
    }



    @Override
    @Transactional
    public List<Results_view> select() {

        List<Results> results = rslDao.select();
        
        List<Results_view> results_view = new ArrayList<>();
        Results_view result_view = null;
        for (Results result:results){
                  result_view = new Results_view(result);
                  results_view.add(result_view);
        }
        return results_view;
    }

    
    
    
    
}
