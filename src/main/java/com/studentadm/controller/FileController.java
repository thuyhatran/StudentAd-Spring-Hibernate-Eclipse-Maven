package com.studentadm.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentadm.service.CourseService;
import com.studentadm.service.ResultService;
import com.studentadm.service.StudentService;

@Controller
public class FileController {
	//initializing the logger
    static Logger log = Logger.getLogger(CourseController.class.getName());
	
    @Autowired
    StudentService studentService;
    
	@Autowired
    CourseService courseService;
	
	@Autowired
    ResultService resultService;
	
	//Open form
	@RequestMapping(value =  "fileProcess" , method = RequestMethod.GET)
	public String openForm(){
		
		return "import_form";
	}
	
	//Export Data
	@RequestMapping(value = "fileProcess", params ="export", method = RequestMethod.POST)
	public String exportData(@RequestParam("imptStudent") String imptStudent, 
							 @RequestParam("fnStudent") String fnStudent,
							 @RequestParam("imptCourse") String imptCourse,
							 @RequestParam("fnCourse") String fnCourse,
							 @RequestParam("imptResult") String imptResult,
							 @RequestParam("fnResult") String fnResult
							 ){
		   
		  log.debug("Student export: " + imptStudent);
		  log.debug("Course export: " + imptCourse);
		  log.debug("Result export: " + imptResult);
          
          if (imptStudent!=null){
                  
                  if (fnStudent!=null)
                	  studentService.write_to_file(fnStudent);
                  else
                	  studentService.write_to_file("C:/import/student.txt");
             
          }

           if (imptCourse!=null){   
                  
                  if (fnCourse!=null)
                      courseService.write_to_file(fnCourse);
                  else
                	  courseService.write_to_file("C:/import/course.txt");
             
          }

           if (imptResult!=null){
         
                  if (fnResult!=null)
                	  resultService.write_to_file(fnResult);
                  else
                	  resultService.write_to_file("C:/import/result.txt");
             
          }
		   
           return "redirect:/";
		   
	}
	
	
	//Import Data from files
	@RequestMapping(value = "fileProcess", params ="import", method = RequestMethod.POST)
	public String importData(@RequestParam("imptStudent") String imptStudent, 
							 @RequestParam("fnStudent") String fnStudent,
							 @RequestParam("imptCourse") String imptCourse,
							 @RequestParam("fnCourse") String fnCourse,
							 @RequestParam("imptResult") String imptResult,
							 @RequestParam("fnResult") String fnResult
							 ){
		   
		  log.debug("Student export: " + imptStudent);
		  log.debug("Course export: " + imptCourse);
		  log.debug("Result export: " + imptResult);
          
          if (imptStudent!=null){
                  
                  if (fnStudent!=null)
                	  studentService.insert_from_file(fnStudent);
                  else
                	  log.error("File for import, " + fnStudent +", not found");
             
          }

           if (imptCourse!=null){   
                  
                  if (fnCourse!=null)
                      courseService.insert_from_file(fnCourse);
                  else
                	  log.error("File for import, " + fnCourse +", not found");
             
          }

           if (imptResult!=null){
         
                  if (fnResult!=null)
                	  resultService.insert_from_file(fnResult);
                  else
                	  log.error("File for import, " + fnResult +", not found");
             
          }
		   
           return "redirect:/";
		   
	}
	
	
	
	
	

}
