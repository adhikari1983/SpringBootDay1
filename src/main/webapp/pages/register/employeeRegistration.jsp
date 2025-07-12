<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
                                    <title>Registration Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <!-- ✅ Move CSS inside head -->
  <style>
    body {
      background-color: #f8f9fa;
    }

    .form-container {
      background-color: light gray;
      border-radius: 10px;
      padding: 30px;
      margin-top: 20px;
      box-shadow: 120px 120px 120px rgba(0,0,0,0.06);
    }

    .form-title {
      color: #007bff;
      font-weight: bold;
    }

    .narrow-form {
      max-width: 400px;
      margin: 0 auto;
    }

    img {
      display: block;
      margin: 0 auto;
    }

    ::placeholder {
      color: #e0e0e0;
      opacity: 0.4;
      font-style: italic;
    }

    h2, p {
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
    }
  </style>
</head>

<body>
<div class="container">

  <h2 class="text-center">Registration Form</h2>
  <p class="text-center text-muted">PLEASE FILL UP THE FOLLOWING INFORMATION</p>
  <img src="images/regs.jpg" width="70" height="70" alt="Regulations Icon" class="mb-1">

  <form action="registration" method="post" class="narrow-form">

                <div class="form-group">
                  <label for="employeeId">Employee Id:</label>
                  <input type="text" class="form-control" placeholder="Enter employee Id" name="employeeId">
                </div>

                <div class="form-group">
                  <label for="employeeName">Employee Name:</label>
                  <input type="text" class="form-control" placeholder="Enter employee name" name="employeeName">
                </div>

                <div class="form-group">
                  <label for="salary">Salary:</label>
                  <input type="text" class="form-control" placeholder="Enter salary" name="salary">
                </div>

                <div class="form-group">
                  <label for="emailId">Email Address:</label>
                  <input type="text" class="form-control" placeholder="Enter email address" name="emailId">
                </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" placeholder="Enter password" name="password">
    </div>

            <div
                    class="text-center mt-4">
                    <button type="submit" class="btn btn-primary px-4">Register</button>
                    <button type="reset" class="btn btn-danger px-4 ml-5">Clear</button>
            </div>
  </form>
</div>
</body>
</html>
