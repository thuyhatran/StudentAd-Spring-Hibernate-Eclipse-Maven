<%-- 
    Document   : course_list
    Created on : Jun 10, 2016, 2:03:20 PM
    Author     : Thuy Ha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"> <%@include file="/WEB-INF/views/CSSs/result_list.css" %></style>
        <!-- <link rel="stylesheet" type="text/css" href="CSSs/result_list.css"> -->
        <title>Course List</title>
    </head>
    <body>
        <header>
             <c:import url="/WEB-INF/views/includes/header.jsp" />
        </header>
        
        <c:import url="/WEB-INF/views/includes/homepage_link.jsp" />   <br><br>
        
        <h2 style="text-align: center">STUDENT RESULTS</h2>
               
        <table>
               <tr>
                   <th>Student ID</th>
                   <th>Course ID</th>
                   <th>Mark 1</th>
                   <th>Mark 2</th>
                   
                   <th colspan="2">Action</th>
               </tr>
               <c:forEach var="rls" items="${results}">
                   <tr>
                       <td><c:out value ="${rls.student_id}" /></td>
                       <td><c:out value ="${rls.course_id}" /></td>
                       <td><c:out value ="${rls.mark1}" /></td>
                       <td><c:out value ="${rls.mark2}" /></td>
                       
                       <td><a href="<c:url value ='/result/edit${rls.student_id}-${rls.course_id}'/>">Edit</a></td>
                       <td><a href="<c:url value ='/result/delete${rls.student_id}-${rls.course_id}'/>">Delete</a></td>
                                                             

                   </tr>

               </c:forEach>

        </table>
        <br>
    </body>
    
    <footer>
        <c:import url="/WEB-INF/views/includes/footer.jsp" />
    </footer>
        
</html>
