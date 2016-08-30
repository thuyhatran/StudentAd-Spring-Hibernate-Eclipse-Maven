<%-- 
    Document   : course_form
    Created on : Jun 10, 2016, 2:03:07 PM
    Author     : Thuy Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Form</title>
        <style type="text/css"> <%@include file="/WEB-INF/views/CSSs/course_form.css" %></style>
        <!-- <link rel="stylesheet" type="text/css" href="CSSs/course_form.css">  -->
    </head>
    <body>
        
	      <header> 
	          	<c:import url="../includes/header.jsp" />        
		        </header>
		        
		         <c:import url="/WEB-INF/views/includes/homepage_link.jsp" />   <br>
		        
		        <form action="courseProcess" method="post">
	                       
	            <fieldset>
	                <legend style="color:rgb(122,41,70);">Course Informations</legend><br>
	                    <label>Course ID:</label>
	                   <input type="text" name="course_id" id="course_id" value="${course_id}" ${id_readonly}
	                           pattern="[0-9]{1,2}" placeholder="1-2 digits"> 
	                    
	                   <%--                  
	                    <select  name="course_id" id="course_id" value="${course_id}" ${id_readonly}
	                             pattern="[0-9]{1,2}" placeholder="1-2 digits">
	                        
	                        <c:forEach var="courseID" items="${CourseIDs}">
	                            <option><c:out value="${courseID}"/></option>
	                        </c:forEach>
	                    
	                    </select> --%>
	
	                    <br><br>
	                    <label>Course Name:</label>
	                    <input type="text" name="course_name" id="course_name" value="${course.course_name}" >
	                    <br><br>
	                    
	            </fieldset>

                 <fieldset id="buttons">
                     <input type="submit" name="new" id="new"    value="New" ${new_disabled}
                            >
                     <input type="submit" name="insert" id="insert" value="Insert" ${insert_disabled}
                            >
                     <input type="submit" name="update" id="update" value="Update" ${update_disabled}>
                     <input type="submit" name="delete" id="delete" value="Delete" ${delete_disabled}>
                     <input type="submit" name="search" id="search" value="Search" ${search_disabled}>
                     <input type="reset" id="reset" value="Reset"><br>
                            
                 </fieldset>

           </form>
                        
      <!--    <script>
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
