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
        <!--  Shows  the success or error message after deletion -->
        <c:if test="${not empty message}">
            <div class="alert alert-info" role="alert">
                <h2>${message}</h2>
            </div>
        </c:if>
        <h2 style="background-color: gold; color: blue;">Employee Information</h2>
        <table class="table table-hover">
            <thead>
                <tr bgcolor="gray">
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
                    <tr bgcolor="coffee">
                        <td>${oneObject.employeeId}</td>
                        <td>${oneObject.employeeName}</td>
                        <td>${oneObject.emailId}</td>
                        <td>${oneObject.password}</td>
                        <td>${oneObject.salary}</td>
                        <!-- <td><a href="deleteEmployee?employeeId=${oneObject.employeeId}" >
                                 <button type="button" class="btn btn-success">
                                 <img alt="delete logo" src="images/delete.png" style="height:20px;width:20px">
                                 </button>
                                 </a>
                        </td> -->
                        <td>
                            <form action="deleteEmployee" method="post" style="display:inline;">
                                <input type="hidden" name="employeeId" value="${oneObject.employeeId}" />
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this employee?');">
                                    <img alt="delete logo" src="images/delete.png" style="height:20px;width:20px">
                                </button>
                            </form>
                        </td>
                        <td><a href="updateEmployee?employeeId=${oneObject.employeeId}">
                                <button type="button" class="btn btn-warning">
                                    <img alt="edit logo logo" src="images/edit.jpg" style="height:20px;width:20px">
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>

</html>