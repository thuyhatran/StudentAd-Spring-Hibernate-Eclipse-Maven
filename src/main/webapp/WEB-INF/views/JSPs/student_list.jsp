<%-- 
    Document   : student_list
    Created on : Jun 3, 2016, 3:15:55 PM
    Author     : Thuy Ha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css"> <%@include file="/WEB-INF/views/CSSs/student_list.css" %></style>
        <title>Student List</title>
    </head>
    <body>
        <header>
             <c:import url="/WEB-INF/views/includes/header.jsp" />
        </header>
        
        <c:import url="/WEB-INF/views/includes/homepage_link.jsp" />   <br><br>
        
        <h2 style="text-align: center">LIST OF STUDENTS</h2>
               
        <table>
                   <tr>
                       <th>ID</th>
                       <th>First Name</th>
                       <th>Last Name</th>
                       <th>Gender</th>
                       <th>Email</th>
                       <th>Start Date</th>
                       <th colspan="4">Action</th>
                   </tr>
               <c:forEach var="st" items="${students}">
                   <tr>
                       <td><c:out value ="${st.student_id}" /></td>
                       <td><c:out value ="${st.first_name}" /></td>
                       <td><c:out value ="${st.last_name}" /></td>
                       <td><c:out value ="${st.gender}" /></td>
                       <td><c:out value ="${st.email}" /></td>
                       <td><c:out value ="${st.start_date}" /></td>
                       
                        
                       <td><a href="<c:url value ='/student/edit${st.student_id}'/>">Edit</a></td>
                       <td><a href="<c:url value ='/student/delete${st.student_id}'/>">Delete</a></td>
                       <td><a href="<c:url value ='/student/email${st.email}'/>">Email</a></td>
                       <td><a href="<c:url value ='/student/transcript${st.student_id}'/>">Transcript</a></td>
                      

                   </tr>

               </c:forEach>

        </table>
        <br>
    </body>
    
    <footer>
        <c:import url="/WEB-INF/views/includes/footer.jsp" />
    </footer>
        
</html>