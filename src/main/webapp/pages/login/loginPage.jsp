<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <!-- custom CSS  -->
    <style>
      body {
        background-color: #f8f9fa;
      }

      .form-container {
        background-color: white;
        border-radius: 10px;
        padding: 30px;
        margin-top: 40px;
        box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
      }

    .narrow-form {
      max-width: 400px;
      margin: 0 auto;
    }
          .form-title {
        color: #007bff;
        font-weight: bold;
      }

      img {
        display: block;
        margin: 0 auto;
      }
        h2 {
          text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
           p {
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
              }
    </style>
</head>
<body>
<div class="container">
  <h2 class="text-center">Login Form</h2>
  <img src="images/login.jpg" width="100" height="100" alt="missing picture">
            <marquee scrollamount="14">
            <h2 style="color: green; font-weight: bold; text-shadow: 1px 1px 2px coffee;">
            ${message}</h2>
            </marquee>
<form action="loginValidate" method="post" class="narrow-form">

    <div class="form-group">
      <label for="emailId">Email Address:</label>
      <input type="text" class="form-control"  placeholder="Enter email address" name="emailId">
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" class="form-control" placeholder="Enter password" name="password">
    </div>

  <div class="text-center mt-4">
      <button type="submit" class="btn btn-primary px-4">Submit</button>
      <button type="reset" class="btn btn-danger px-4 ml-5">Reset</button>
    </div>
</form>


</div>

</body>
</html>