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

import com.studentadm.model.Course;
import com.studentadm.service.CourseService;
import com.studentadm.service.ResultService;

import org.apache.log4j.Logger;

/**
 *
 * @author Thuy Ha
 */

@Controller
@RequestMapping("/course")
public class CourseController{
	
	//initializing the logger
    static Logger log = Logger.getLogger(CourseController.class.getName());
	
	@Autowired
    CourseService courseService;
	
	@Autowired
    ResultService resultService;
	
    /*
    * Display all courses.
    * path is "/course" or "/course/list_course"  after application path
    */
   @RequestMapping(value = { "/", "/listall" }, method = RequestMethod.GET)
   public String listCourse(ModelMap model) {
   	
       List<Course> courses = courseService.select();
       
       for (Course course:courses)
       		log.debug(course);
       
       model.addAttribute("courses", courses);
       
       return "course_list";
   }	
 
   //Open Course Form
   @RequestMapping(value = { "/form" }, method = RequestMethod.GET)
   public String formCourse(ModelMap model){
	   
	   model.addAttribute("id_readonly","");  //set course_id field to non-readonly
	   model.addAttribute("new_disabled", "");
	   model.addAttribute("insert_disabled", "disabled");
	   model.addAttribute("update_disabled", "disabled");
	   model.addAttribute("search_disabled", "");
	   model.addAttribute("delete_disabled", "disabled");
	   
	   return "course_form";
   }
   
   
   
   @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
   public String newCourse(ModelMap model) {
	   
	   int course_id = courseService.getNewCourseID();
       Course course = new Course(course_id);
       
       log.debug("Creating ... new course");
       
       model.addAttribute("course", course);
      // model.addAttribute("edit", false);
       
       model.addAttribute("course_id", course_id);
       model.addAttribute("id_readonly","readonly");  //set course_id field to readonly
       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "");
       model.addAttribute("update_disabled", "disabled");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "disabled");
       
       return "course_form";
   }
   
   // Insert/save new course
   @RequestMapping(value={"/new"}, method = RequestMethod.POST)
   public String insertCourse(Course course, ModelMap model){
	   
	   courseService.insert(course);
	   
	   log.debug(" new course inserted" + course);
	   
	   return "redirect:/course/listall";  //after inserting, display all courses
   }
   
   //Update a course
   @RequestMapping(value = {"/edit{course_id}"}, method = RequestMethod.GET)
   public String editCourse(@PathVariable int course_id, ModelMap model){
	   
	   Course course = courseService.selectById(course_id);
	   model.addAttribute("course",course);
	   
	   model.addAttribute("id_readonly","readonly");  //set course_id field to readonly          
       
       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "disabled");
       model.addAttribute("update_disabled", "");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "");
	   
	   return "course_form";
	   
   }
   
   //Update a course
   @RequestMapping(value = {"/edit{course_id}"}, method = RequestMethod.POST)
   public String updateCourse(Course course){
	   
	   courseService.update(course);
	   
	   log.debug(" course updated" + course);
	   
	   return "redirect:/course/listall";
	   
   }
   
   
   //Delete a course  
   @RequestMapping(value = {"/delete{course_id}"}, method = RequestMethod.GET)
   public String deleteCourse(@PathVariable int course_id){
	   
	   //delete all result of this course   
       resultService.deleteCourse(course_id);
       
       //delete this course    
       courseService.delete(course_id);
       
       log.debug(" Delete course " + course_id + " and all related results");
 
       return "redirect:/course/listall";
	   
   }
   
   //Display a course  
   @RequestMapping(value = {"/find{course_id}"}, method = RequestMethod.GET)
   public String displayCourse(@PathVariable int course_id, ModelMap model){
	   
	   Course course = courseService.selectById(course_id);
	   
	   log.debug(course);
	   
       if (course ==null){
           
           //JFrame parent = new JFrame();
           //JOptionPane.showMessageDialog(null, "Not found!");
        
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("update_disabled","disabled");
            model.addAttribute("delete_disabled","disabled");
           
       }else{
                   
            model.addAttribute("course_id", course_id);
            model.addAttribute("course", course);
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("id_readonly","");
       }


       return "course_form"; 
   }
   
   
   /*
   //This way below is working similar to String listCourse() above (return a string), this is the way of older String version
   @RequestMapping(value="/list_course", method=RequestMethod.GET)
	public ModelAndView listCourse(){
   	
		ModelAndView model = new ModelAndView("course_list");
		
		 List<Course> courses = courseService.select();
	        
	        for (Course course1:courses)
	        	System.out.println(course1);
	        
	       model.addObject(courses);

		return model;
	}
	*/

    
 
}
