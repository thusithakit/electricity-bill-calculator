<%--
  Created by IntelliJ IDEA.
  User: thusi
  Date: 3/1/2023
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Electricity Bill Calculator</title>
  <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%
request.getAttribute("status");
%>">
<div class="wrapper">
  <div class="left-sec">
    <img src="./images/ceb-logo.png" alt="" class="logo">
    <div>
      <h1>Welcome To the Future of <br><span>Ceylon Electricity Board</span></h1>
      <h5>“Enrich Life Through Power”</h5>
    </div>
  </div>
  <div class="right-sec">
    <h2>Log In</h2>
    <form action="login" method="post">
      <div>
        <label for="username">User Name</label>
        <input type="text" name="username" id="username">
      </div>
      <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
      </div>
      <span>
                    <a href="register.jsp">Sign Up</a>
                <a href="">Forgot Password</a>
                </span>
      <button type="submit">Log In</button>
    </form>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<%--<script type="text/javascript">--%>
<%--  var status = document.getElementById("status").value;--%>
<%--  if (status=="success") {--%>
<%--    Swal.fire("Congrats", "Account Created Successfully", "success");--%>
<%--  } else {--%>
<%--    Swal.fire("Failed", "Error", "error");--%>
<%--  }--%>
<%--</script>--%>
</body>
</html>
