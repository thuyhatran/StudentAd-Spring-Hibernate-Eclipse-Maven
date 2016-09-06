/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.studentadm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentadm.model.Student;
import com.studentadm.model.StudentsGrade;
import com.studentadm.service.StudentService;
import com.studentadm.service.ResultService;

import org.apache.log4j.Logger;

/**
*
* @author Thuy Ha
* 
*/

@Controller
@RequestMapping("/student")
public class StudentController{
	
	//initializing the logger
    static Logger log = Logger.getLogger(StudentController.class.getName());
	
	@Autowired
    StudentService studentService;
	
	@Autowired
    ResultService resultService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, "start_date", new CustomDateEditor(dateFormat, false));

	}
	
	
	
    /*
    * Display all students.
    * path is "/student" or "/student/list_student"  after application path
    * 
    */
   @RequestMapping(value = { "/", "/listall" }, method = RequestMethod.GET)
   public String listStudent(ModelMap model) {
   	
       List<Student> students = studentService.select();
       
       for (Student student:students)
       		log.debug(student);
       
       model.addAttribute("students", students);
       
       return "student_list";
   }	
 
 
   
   @RequestMapping(value = { "/form" }, method = RequestMethod.GET)
   public String formStudent(ModelMap model){
	   
	   model.addAttribute("stid_readonly","");  //set student_id field to readonly
	   model.addAttribute("new_disabled", "");
	   model.addAttribute("insert_disabled", "disabled");
	   model.addAttribute("update_disabled", "disabled");
	   model.addAttribute("search_disabled", "");
	   model.addAttribute("delete_disabled", "disabled");
	   
	   return "student_form";
   }
   
   
   
   @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
   public String newStudent(ModelMap model) {
	   
	   int student_id = studentService.getNewStudentID();
       Student student = new Student(student_id);
       
       log.debug("Creating ... new student");
       
       model.addAttribute("student", student);
      // model.addAttribute("edit", false);
       
       model.addAttribute("student_id", student_id);
       model.addAttribute("stid_readonly","readonly");  //set student_id field to readonly
       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "");
       model.addAttribute("update_disabled", "disabled");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "disabled");
       
       return "student_form";
   }
   
   
   //Update a student
   @RequestMapping(value = {"/edit{student_id}"}, method = RequestMethod.GET)
   public String editStudent(@PathVariable int student_id, ModelMap model){
	   
	   Student student = studentService.selectById(student_id);
	   model.addAttribute("student",student);
	   
	   model.addAttribute("id_readonly","readonly");  //set student_id field to readonly          
       
       model.addAttribute("new_disabled", "disabled");
       model.addAttribute("insert_disabled", "disabled");
       model.addAttribute("update_disabled", "");
       model.addAttribute("search_disabled", "disabled");
       model.addAttribute("delete_disabled", "");
	   
	   return "student_form";
	   
   }
   
   
   //Delete a student  
   @RequestMapping(value = {"/delete{student_id}"}, method = RequestMethod.GET)
   public String deleteStudent(@PathVariable int student_id){
	   
	   //delete all result of this student   
       resultService.deleteStudent(student_id);
       
       //delete this student    
       studentService.delete(student_id);
       
       log.debug(" Delete student " + student_id + " and all related results");
 
       return "redirect:/student/listall";
	   
   }
   
   //Display a student  
   @RequestMapping(value = {"/find{student_id}"}, method = RequestMethod.GET)
   public String displayStudent(@PathVariable int student_id, ModelMap model){
	   
	   Student student = studentService.selectById(student_id);
	   
	   log.debug(student);
	   
       if (student ==null){
           
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("update_disabled","disabled");
            model.addAttribute("delete_disabled","disabled");
           
       }else{
                   
            model.addAttribute("student_id", student_id);
            model.addAttribute("student", student);
            model.addAttribute("insert_disabled", "disabled");
            model.addAttribute("stid_readonly","");
       }

       return "student_form"; 
   }
   
   
 //---------------------------------------------------
   //
   // All methods related to button clicked in course_form
   //
   //----------------------------------------------------
   
   @RequestMapping(value = "studentProcess", params ="new", method = RequestMethod.POST)
   public String newStudentClicked() {
       return "redirect:/student/new";
   }
   
   //Update a course
   @RequestMapping(value = "studentProcess", params ="update", method = RequestMethod.POST)
   public String updateStudent(Student student){
	   
	   studentService.update(student);
	   
	   log.debug(" student updated" + student);
	   
	   return "redirect:/student/listall";
	   
   }
   
   //Insert a student
   @RequestMapping(value = "studentProcess", params ="insert", method = RequestMethod.POST)
   public String insertStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, ModelMap model){
	   
	   if (result.hasErrors()){
		   
		   model.addAttribute("stid_readonly","");  //set student_id field to readonly
		   model.addAttribute("new_disabled", "");
		   model.addAttribute("insert_disabled", "disabled");
		   model.addAttribute("update_disabled", "disabled");
		   model.addAttribute("search_disabled", "");
		   model.addAttribute("delete_disabled", "disabled");
		   
		   return "student_form";
	   }
	   
	   studentService.insert(student);
	   
	   log.debug(" new student inserted" + student);
	   
	   return "redirect:/student/listall";  //after inserting, display all students
   }
   
  @RequestMapping(value = "studentProcess", params ="delete", method = RequestMethod.POST)
   public String deleteStudentClicked(@RequestParam("student_id") int student_id){
	   
 
       return "redirect:/student/delete"+student_id;
   }
   
   
 //Display a student  
   @RequestMapping(value = "studentProcess", params ="search", method = RequestMethod.POST)
   public String searchStudent(@RequestParam("student_id") int student_id){
	   
       return "redirect:/student/find"+student_id; 
   }
   

   
   // Display all grades
   @RequestMapping(value = { "/grades" }, method = RequestMethod.GET)
   public String studentGrades(ModelMap model) {

       List<StudentsGrade> stdGrade = studentService.getGrades();
              
       model.addAttribute("studentsGrade", stdGrade);
       
       return "students_grade";
   }
   
   
   //Student Transcript
   @RequestMapping(value = { "/transcript{student_id}" }, method = RequestMethod.GET)
   public String studentTranscript(@PathVariable int student_id, ModelMap model) {
	   
       List<StudentsGrade> stdGrade = studentService.getTranscript(student_id);
              
       model.addAttribute("students", stdGrade);
       
       return "student_transcript";
   }
   
   //Open email form
   @RequestMapping(value = { "/email{address}" }, method = RequestMethod.GET)
   public String emailForm(@PathVariable String address, ModelMap model) {  
              
       model.addAttribute("email", address);
       
       return "email_form";
   }
   
   
   //sending email
   @RequestMapping(value = { "/email" }, method = RequestMethod.POST)
   public String emailSend(@RequestParam("email") String address, @RequestParam("subject") String subject,  @RequestParam("content") String content  ){
	   

	   final String username="javacourse2016@gmail.com";
       final String password ="";
       Properties props = new Properties();
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");
       //Session session = Session.getDefaultInstance(props);
       Session session = Session.getInstance(props,
       new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
               }
         });   

       try {

               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress("javacourse2016@gmail.com"));
               message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(address));
               message.setSubject(subject);
               message.setText(content);

               Transport.send(message);

               System.out.println("Done");

       } catch (MessagingException e) {
               throw new RuntimeException(e);
       }

 
       return "redirect:/student/listall";
	   
   }
   

     
   
   
   /*
   //This way below is working similar to String listStudent() above (return a string), this is the way of older String version
   @RequestMapping(value="/list_student", method=RequestMethod.GET)
	public ModelAndView listStudent(){
   	
		ModelAndView model = new ModelAndView("student_list");
		
		 List<Student> students = studentService.select();
	        
	        for (Student student1:students)
	        	System.out.println(student1);
	        
	       model.addObject(students);

		return model;
	}
	*/
   

}





