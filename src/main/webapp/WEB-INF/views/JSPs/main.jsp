<%-- 
    Document   : menu
    Created on : 1-Jun-2016, 3:46:40 PM
    Author     : thuyha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <!--  <link rel="stylesheet" type="text/css" href="CSSs/main_styles.css">  --> 

 		<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/WEB-INF/views/CSSs/main_styles.css"/>">  --%>
 		
 		<style type="text/css">	<%@include file="/WEB-INF/views/CSSs/main_styles.css" %></style>
    
	    
 
	   <title>Student Administration</title>
	    
	</head>	
	
	<body>
		
	    <header> 
	    
	          <c:import url="/WEB-INF/views/includes/header.jsp" />
	        
	    </header>

	    <section>
	    
	    	<a href="?siteLanguage=en">English</a>|
 			<a href="?siteLanguage=fr">Francais</a>
			
		<%-- 	<spring:message code="label.headline"/>  --%>
	        
	        <h2 style="text-align: center; color:green">Student Administration</h2>
	        <div class="dropdown">
	          <button class="dropbtn">Student</button>
	          <div class="dropdown-content">
	              
	            <a href="<c:url value ='/student/form'/>">Student Form</a>	
	            
	            <a href="<c:url value ='/student/listall'/>">Student List</a>	
	                         
	            <a href="<c:url value ='/student/grades'/>">Student Grade</a>	        
	            
	              
	          </div>
	        </div>
	
	        <div class="dropdown">
	          <button class="dropbtn">Course</button>
	          <div class="dropdown-content">
	          	
	          	<a href="<c:url value ='/course/form'/>">Course Form</a>	
	          	<a href="<c:url value ='/course/listall'/>">Course List</a>	
	                        
	          </div>
	        </div> 
	
	        <div class="dropdown">
	          <button class="dropbtn">Result</button>
	          <div class="dropdown-content">
	              
	            <a href="<c:url value ='/result/form'/>">Result Form</a>	
	          	<a href="<c:url value ='/result/listall'/>">Result List</a>	
	            
	          </div>
	        </div> 
	        
	        <div class="dropdown">
	          <button class="dropbtn">
	         	<a style="text-decoration: none; color:white" href="<c:url value ='/fileProcess' />">Import/Export</a> 
	          </button>
	          
	        </div> 
	       
	
	       </section>
	    <footer>
	        <c:import url="/WEB-INF/views/includes/footer.jsp" />
	    </footer>
	</body>
	
	
</html>
