<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <title> Delete Confirmation</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
        <form class="text-success text-center">
            <div>
                    <h2 style="color: #007bff; font-weight: bold; text-shadow: 1px 1px 2px gray;">
                     ${message}
                     </h2 >
             </div>
             <div>
                     <a href="showEmployee" class="btn btn-warning" style="color: #007bff; font-weight: bold; text-shadow: 1px 1px 2px gray;">
                            Back to Employee List
                     </a>
             </div>
         </form>
</body>

</html>