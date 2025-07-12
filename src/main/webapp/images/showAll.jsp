<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>response page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Employee Information</h2>
  
  <table class="table table-hover">
    <thead>
      <tr bgcolor="yellow">
        <th>Employee Id</th>
        <th>Employee Name</th>
        <th>Email Address</th>
        <th>Password</th>
        <th>Salary</th>
        <th colspan=2>Action(delete/update)</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach var="oneObject" items="${employeeDtoList}">
      <tr  bgcolor="pink">
       <td>${oneObject.employeeId}</td>
       <td>${oneObject.employeeName}</td>
       <td>${oneObject.emailId}</td>
       <td>${oneObject.password}</td>
       <td>${oneObject.salary}</td>
       <td><a href="deleteEmployee?employeeId=${oneObject.employeeId}" >
              <button type="button" class="btn btn-success">
                   <img alt="delete logo" src="images/delete.png" style="height:30px;width:30px">
              </button>
            </a>
       </td>
        <td><a href="updateEmployee?employeeId=${oneObject.employeeId}" >
              <button type="button" class="btn btn-primary">
                  <img alt="edit logo logo" src="images/edit.jpg" style="height:30px;width:30px">
              </button>
            </a>
        </td>
      </tr>
   </c:forEach>   
      
    </tbody>
  </table>
  <a href="showEmployee"> <button type="button" class=btn btn-primary">show all records</button></a>
</div>
</body>
</html>