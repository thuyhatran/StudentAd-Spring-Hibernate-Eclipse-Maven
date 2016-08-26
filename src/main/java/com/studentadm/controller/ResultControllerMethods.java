/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.controller;

import com.studentadm.service.CourseService;
import com.studentadm.service.ResultService;
import com.studentadm.service.ResultViewService;
import com.studentadm.service.StudentService;
import com.studentadm.model.Course;
import com.studentadm.model.Results;
import com.studentadm.model.Results_view;
import com.studentadm.model.Student;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Administrator
 */
public class ResultControllerMethods {
    
    //from menu
    static public void resultFormCalled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //This code to create value for combobox
//        courseDao stDao = new courseDao();
//        ArrayList<Integer> CourseIDs = null;
//        
//        try {
//            
//            CourseIDs = stDao.getAllCourseID();
//            
//        } catch (SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(ResultControllerMethods.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        request.setAttribute("CourseIDs",CourseIDs);

        request.setAttribute("stid_readonly","");  //set course_id field to readonly
        request.setAttribute("new_disabled", "");
        request.setAttribute("insert_disabled", "disabled");
        request.setAttribute("update_disabled", "disabled");
        request.setAttribute("search_disabled", "");
        request.setAttribute("delete_disabled", "disabled");

    }
    
    static public void newResultCalled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

                request.setAttribute("new_disabled", "disabled");
                request.setAttribute("insert_disabled", "");
                request.setAttribute("update_disabled", "disabled");
                request.setAttribute("search_disabled", "disabled");
                request.setAttribute("delete_disabled", "disabled");
                
               
    }
    
    static public void listAllCalled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        ResultService rlsService = new ResultService();
//        List<Results> results = new ArrayList<>();     
//        results =  rlsService.select();
        
        ResultViewService rlsViewService = new ResultViewService();
        List<Results_view> results_view = rlsViewService.select();
            
        request.setAttribute("results", results_view);
    }
    
    //from course_list
    
    static public void deleteCalled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        
        ResultService crsService = new ResultService();
        crsService.delete(student_id,course_id);
                      
        //Prepared for refresh page with result's list
        ResultViewService rlsViewService = new ResultViewService();
        List<Results_view> results_view = rlsViewService.select();

        request.setAttribute("results", results_view);
            
        
      
    }
    
    static public void editCalled(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
            
            //Get information of the course tha has the course_id getting from course_list 
            //and then send this information to course_form to do the modification
            
            //get course's info
            int student_id = Integer.parseInt(request.getParameter("student_id"));
            int course_id = Integer.parseInt(request.getParameter("course_id"));
            ResultViewService rslService = new ResultViewService();            
            //Results result = rslService.selectById(student_id,course_id); 
            
            Results_view result_view = rslService.selectById(student_id,course_id);
            // set attribute 
             
            request.setAttribute("result",result_view); 
            request.setAttribute("id_readonly","readonly");  //set course_id field to readonly          
            
            request.setAttribute("new_disabled", "disabled");
            request.setAttribute("insert_disabled", "disabled");
            request.setAttribute("update_disabled", "");
            request.setAttribute("search_disabled", "disabled");
            request.setAttribute("delete_disabled", "");
                
    }

    

    //---- for doPost----
    static public void insertClicked(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException{

            int student_id = Integer.parseInt(request.getParameter("student_id"));
            int course_id = Integer.parseInt(request.getParameter("course_id"));
            int mark1 = Integer.parseInt(request.getParameter("mark1"));
            int mark2 = Integer.parseInt(request.getParameter("mark2"));
            
        //    Results rsl = new Results(student_id,course_id,mark1,mark2);
            
            ResultService rlsService = new ResultService();
            StudentService stService = new StudentService();
            CourseService crsService = new CourseService();
            
            Student st = stService.selectById(student_id);
            Course crs = crsService.selectById(course_id);
            
            if ((st==null) || (crs==null))
                System.out.println("Student or Course is null");
            else{
            
                Results rsl = new Results(mark1,mark2);
                rsl.setStudent(st);
                rsl.setCourse(crs);

                System.out.println(rsl);

                rlsService.insert(rsl);
            }

            //prepared to forward to course_list
            ResultViewService rlsViewService = new ResultViewService();
            List<Results_view> results_view = rlsViewService.select();

            request.setAttribute("results", results_view);
         
    }
    

    
      static public void deleteClicked(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException{

//            int student_id = Integer.parseInt(request.getParameter("student_id"));
//            int course_id = Integer.parseInt(request.getParameter("course_id"));
//            
//             //delete all result of this course
//            ResultService rlsService = new ResultService();
//            rlsService.delete(student_id,course_id);
//
//            //prepared to forward to course_list
//            ResultViewService rlsViewService = new ResultViewService();
//            List<Results_view> results_view = rlsViewService.select();
//
//            request.setAttribute("results", results_view);
            
              deleteCalled(request,response);
            
            
       
    }
    
    static public void searchClicked(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
       
        //This creates value for combobox
//            courseDao stDao_t = new courseDao();
//            ArrayList<Integer> CourseIDs = null;
//
//            try {
//
//                CourseIDs = stDao_t.getAllCourseID();
//
//            } catch (SQLException | ClassNotFoundException ex) {
//                Logger.getLogger(ResultControllerMethods.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            request.setAttribute("CourseIDs",CourseIDs);
        


        
            String st_student_id = request.getParameter("student_id");
            String st_course_id = request.getParameter("course_id");
                                 
            Results_view result = null;
  
            
            if (!((st_student_id.equals(""))&&(st_course_id.equals("")))){
                
                System.out.println("not null");
                int student_id = Integer.parseInt(st_student_id);
                int course_id = Integer.parseInt(st_course_id);
                ResultViewService rlsService = new ResultViewService();
            
                    result= rlsService.selectById(student_id,course_id);

                if (result ==null){
                    
                    //JFrame parent = new JFrame();
                    //JOptionPane.showMessageDialog(null, "Not found!");
                 
                    request.setAttribute("insert_disabled", "disabled");
                    request.setAttribute("update_disabled","disabled");
                    request.setAttribute("delete_disabled","disabled");
                    
                }else{
                            
                    //request.setAttribute("course_id", course_id);
                    request.setAttribute("result", result);
                    request.setAttribute("insert_disabled", "disabled");
                    request.setAttribute("stid_readonly","");
                }

            }else{
                 request.setAttribute("insert_disabled", "disabled");
                 request.setAttribute("update_disabled","disabled");
                 request.setAttribute("delete_disabled","disabled");
                
            }

    }
    
    static public void updateClicked(HttpServletRequest request, HttpServletResponse response){
         
            int student_id = Integer.parseInt(request.getParameter("student_id"));
            int course_id = Integer.parseInt(request.getParameter("course_id"));
            int mark1 = Integer.parseInt(request.getParameter("mark1"));
            int mark2 = Integer.parseInt(request.getParameter("mark2"));
            
            ResultService rlsService = new ResultService();
            StudentService stService = new StudentService();
            CourseService crsService = new CourseService();
            
            Student st = stService.selectById(student_id);
            Course crs = crsService.selectById(course_id);
            
            if ((st==null) || (crs==null))
                System.out.println("Student or Course is null");
            else{
            
                Results rsl = new Results(mark1,mark2);
                rsl.setStudent(st);
                rsl.setCourse(crs);

                System.out.println(rsl);

                rlsService.update(rsl);
            }


             //prepared to forward to course_list
            ResultViewService rlsViewService = new ResultViewService();
            List<Results_view> results_view = rlsViewService.select();

            request.setAttribute("results", results_view);
                        
                      
    }

    
}
