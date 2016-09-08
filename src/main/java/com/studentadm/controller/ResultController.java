/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentadm.model.Course;
import com.studentadm.model.Results;
import com.studentadm.model.Results_view;
import com.studentadm.model.Student;
import com.studentadm.service.ResultService;
import com.studentadm.service.ResultViewService;
import com.studentadm.service.StudentService;
import com.studentadm.service.CourseService;

import org.apache.log4j.Logger;

/**
 *
 * @author Thuy Ha
 */

@Controller
@RequestMapping("/result")
public class ResultController{
	
	//initializing the logger
    static Logger log = Logger.getLogger(ResultController.class.getName());
	
	@Autowired
    ResultService resultService;
	
	@Autowired
    ResultViewService resultViewService;
	
	@Autowired
    StudentService studentService;
	
	@Autowired
    CourseService courseService;
	
    /*
    * Display all results.
    * path is "/result" or "/result/list_result"  after application path
    */
   @RequestMapping(value = { "/", "/listall" }, method = RequestMethod.GET)
   public String listResults(ModelMap model) {
   	
       List<Results_view> results_view = resultViewService.select();
       
       for (Results_view result:results_view)
       		log.debug(result);
       
       model.addAttribute("results", results_view);
       
       return "result_list";
   }	
   
 //Open Result Form
   @RequestMapping(value = { "/form" }, method = RequestMethod.GET)
   public String formResult(ModelMap model){
	   
	   model.addAttribute("new_disabled", "");
	   model.addAttribute("insert_disabled", "disabled");
	   model.addAttribute("update_disabled", "disabled");
	   model.addAttribute("search_disabled", "");
	   model.addAttribute("delete_disabled", "disabled");
	   
	   return "result_form";
   }
   
   
   @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
   public String newResult(ModelMap model) {

       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "");
       model.addAttribute("update_disabled", "disabled");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "disabled");
       
       return "result_form";
   }
   
   
   //Update a result
   @RequestMapping(value = {"/edit{student_id}-{course_id}"}, method = RequestMethod.GET)
   public String editResult(@PathVariable int student_id, @PathVariable int course_id, ModelMap model){
	   
	   Results_view result = resultViewService.selectById(student_id, course_id);
	   
	   model.addAttribute("result",result);
	   
	   model.addAttribute("id_readonly","readonly");  //set  field to readonly          
       
       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "disabled");
       model.addAttribute("update_disabled", "");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "");
	   
	   return "result_form";
	   
   }
   
   
   
   //Delete a result  
   @RequestMapping(value = {"/delete{student_id}-{course_id}"}, method = RequestMethod.GET)
   public String deleteResult(@PathVariable int student_id, @PathVariable int course_id){
       
       //delete this result    
       resultService.delete(student_id, course_id);
       
       log.debug(" Delete result student_id, course_id: " + student_id+ "  " + course_id );
 
       return "redirect:/result/listall"; 
   }
   
   
   //Display a result  
   @RequestMapping(value = {"/find{student_id}-{course_id}"}, method = RequestMethod.GET)
   public String displayResult(@PathVariable int student_id,@PathVariable int course_id, ModelMap model){
	   
	   Results_view result = resultViewService.selectById(student_id,course_id);
	   
	   log.debug(result);
	   
       if (result ==null){
        
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("update_disabled","disabled");
            model.addAttribute("delete_disabled","disabled");
           
       }else{
                   
            
            model.addAttribute("result", result);
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("stid_readonly","");
       }


       return "result_form"; 
   }
   
   
   //---------------------------------------------------
   //
   // All methods related to button clicked in result_form
   //
   //----------------------------------------------------
   
   @RequestMapping(value = {"/resultProcess"}, params ="new", method = RequestMethod.POST)
   public String newResultClicked() {

       return "redirect:/result/new";
   }
   
   //Update a result
   @RequestMapping(value = {"/resultProcess"}, params ="update", method = RequestMethod.POST)
   public String updateResult(Results_view result_view){
	   
	   Student st = studentService.selectById(result_view.getStudent_id());
       Course crs = courseService.selectById(result_view.getCourse_id());
       
	   
       if ((st==null) || (crs==null))
           log.debug("Trying to updating result, but this Student ID or Course IS is not exist");
       else{
       
           Results result = new Results(result_view.getMark1(),result_view.getMark2());
           result.setStudent(st);
           result.setCourse(crs);

           resultService.update(result);
           
           log.debug("  result updated" + result);
       }
       
	   return "redirect:/result/listall";
	   
   }
   
   //Insert a result
   @RequestMapping(value = {"/resultProcess"}, params ="insert", method = RequestMethod.POST)
   public String insertResult(Results_view result_view){
	   
	   Student st = studentService.selectById(result_view.getStudent_id());
       Course crs = courseService.selectById(result_view.getCourse_id());
       
       if ((st==null) || (crs==null))
           log.error("Trying to inserting new result, but this Student ID or Course IS is not exist");
       else{
       
           Results result = new Results(result_view.getMark1(),result_view.getMark2());
           result.setStudent(st);
           result.setCourse(crs);

           resultService.insert(result);
           
           log.debug(" new result inserted" + result);
       }
	      
	   return "redirect:/result/listall";  //after inserting, display all results
   }
   
   @RequestMapping(value = {"/resultProcess"}, params ="delete", method = RequestMethod.POST)
   public String deleteResultClicked(@RequestParam("student_id") int student_id, @RequestParam("course_id") int course_id){
	   
       return "redirect:/result/delete"+student_id+"-"+course_id;
   }
   
   
  //Display a course  
   @RequestMapping(value = {"/resultProcess"}, params ="search", method = RequestMethod.POST)
   public String searchResult(@RequestParam("student_id") int student_id, @RequestParam("course_id") int course_id){

       return "redirect:/result/find"+student_id+"-"+course_id;
   }
   

 
}