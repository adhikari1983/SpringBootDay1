<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login confirmation</title>
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
        <marquee scrollamount="10">
            <h2
                style="
                    color: #007bff;
                    font-weight: bold;
                    text-shadow: 1px 1px 2px gray;
                "
            >
                Congratulations!!! ${employeeDTO.employeeName}.. You have
                successfully logged-in.
            </h2>
        </marquee>

        <div class="container">
            <table class="table table-hover">
                <thead>
                    <tr bgcolor="gray">
                        <th>Employee Id</th>
                        <th>Employee Name</th>
                        <th>Salary</th>
                        <th>Email Address</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <tr bgcolor="coffee">
                        <td>${employeeDTO.employeeId}</td>
                        <td>${employeeDTO.employeeName}</td>
                        <td>${employeeDTO.salary}</td>
                        <td>${employeeDTO.emailId}</td>
                        <td>${employeeDTO.password}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="text-center mt-1">
           <a href="showEmployee"><button type="button" class="btn btn-warning">click here to get all records</a>
        </div>
    </body>
</html>
