<%-- 
    Document   : NewStudentForm
    Created on : 2-Jun-2016, 2:46:19 PM
    Author     : thuyha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<!DOCTYPE html 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Form</title>
        <style type="text/css"> <%@include file="/WEB-INF/views/CSSs/student_form.css" %></style>
        <!-- <link rel="stylesheet" type="text/css" href="CSSs/student_form.css">  -->
    </head>
    <body>
        
      <header> 
          <c:import url="../includes/header.jsp" />        
        </header>
        
        
		<c:import url="/WEB-INF/views/includes/homepage_link.jsp" />
		<div align="right"> 
		<a href="/StudentApp/student/form?siteLanguage=en">English</a>|
		<a href="/StudentApp/student/form?siteLanguage=fr">Francais</a>
		</div>
		 		
        <form action="studentProcess" method="post">
           
            <fieldset>
                <legend style="color:rgb(122,41,70);"><spring:message code="studentform.legend"/> </legend><br>
                    <label for "student_id"><spring:message code="studentform.student_id"/></label>
                    <input type="number" name="student_id" id="student_id" value="${student_id}" ${stid_readonly}
                           pattern="[0-9]{,3}" placeholder="1-3 digits">
                    <br><br>
                    <label for "first_name"><spring:message code="studentform.first_name"/></label>
                    <input type="text" name="first_name" id="first_name" value="${student.first_name}" >
                    <form:errors path="student.first_name"/>
                    <br><br>
                    <label for "last_name"><spring:message code="studentform.last_name"/></label>
                    <input type="text" name="last_name" id="lastname" value="${student.last_name}">
                    <form:errors path="student.last_name"/>
                    <br><br>
                    <label for "gender"><spring:message code="studentform.gender"/></label>
                    <select name=gender id="gender" value="${student.gender}">
                        <option value="M"><spring:message code="studentform.male"/></option>
                        <option value="F"><spring:message code="studentform.female"/></option>
                    </select><br><br>
                    <label for "email"><spring:message code="studentform.email"/></label>
                    <input type="text" name="email" id="email" value="${student.email}">
                    <form:errors path="student.email"/>
                    <br><br>
                    <label for "start_date"><spring:message code="studentform.start_date"/></label>
                    <input type="text" name="start_date" id="start_date" value="${student.start_date}" 
                           placeholder="yyyy-MM-dd" >
                    <form:errors path="student.start_date"/>
                  
                    </fieldset>

                    <fieldset id="buttons">
                        <input type="submit" name="new" id="new"    value=<spring:message code="studentform.button_new"/> ${new_disabled}>
                        <input type="submit" name="insert" id="insert" value=<spring:message code="studentform.button_insert"/> ${insert_disabled}>
                              
                        <input type="submit" name="update" id="update" value=<spring:message code="studentform.button_update"/> ${update_disabled}>
                        <input type="submit" name="delete" id="delete" value=<spring:message code="studentform.button_delete"/> ${delete_disabled}>
                        <input type="submit" name="search" id="search" value=<spring:message code="studentform.button_search"/> ${search_disabled}>
                        <input type="reset" id="reset" value=<spring:message code="studentform.button_reset"/>>
                               
                    </fieldset>

            </form>
                        
      <!-- <script>
                var d=new Date();    
                document.getElementById("day").value = d.getDate();
                document.getElementById("month").value = d.getMonth()+1;
                document.getElementById("year").value = d.getFullYear();
          </script> 
   
          <script>
              function new_clicked(){
                  document.getElementById("new").disabled = true;
                  document.getElementById("insert").disabled = false;
                  document.getElementById("update").disabled = true;
                  document.getElementById("delete").disabled = true;
                  document.getElementById("search").disabled = true;
              }
              function insert_clicked(){
                  document.getElementById("new").disabled = false;
                  document.getElementById("insert").disabled = true;
                  document.getElementById("update").disabled = true;
                  document.getElementById("delete").disabled = true;
                  document.getElementById("search").disabled = true;
              }
          
          </script>
            -->
   
    
         </body>
    
    
    <footer>
         <c:import url="/WEB-INF/views/includes/footer.jsp" />
    </footer>
    
</html>
