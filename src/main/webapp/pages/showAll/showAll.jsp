<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>showing all employees</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <table class="table table-hover">
                                <thead>
                                    <tr bgcolor="gray">
                                        <th>Employee Id</th>
                                        <th>Employee Name</th>
                                        <th>Salary</th>
                                        <th>Email Address</th>
                                        <th>Password</th>
                                        <th colspan=2>Delete/Edit</th>
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
                                        <td>
            <!--for URI => url + endpoint => http://localhost:444/deleteEmployee?employeeId=111
            (url)href = http://localhost:444  + (endpoint w/queryParameter) = /deleteEmployee?employeeId=111 -->
                                            <a href="deleteEmployee?employeeId=${oneObject.employeeId}"
                                            class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this employee?');">
                                            <img alt="delete logo" src="images/delete.png" style="height:20px;width:20px">
                                            </button>
                                            <a/>
                                        </td>
                                        <td>
                                         <!--right way to do it no <button> </button> inside a tag, on above deleteEmployee -->
                                           <a href="updateEmployee?employeeId=${oneObject.employeeId}"
                                                class="btn btn-warning" style="display: inline-block;">
                                                <img alt="edit logo" src="images/edit.jpg" style="height:20px;width:20px">
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
            </table>
        </div>
    </body>
</html>
