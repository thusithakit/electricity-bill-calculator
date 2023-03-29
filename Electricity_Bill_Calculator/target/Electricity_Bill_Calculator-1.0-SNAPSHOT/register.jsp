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
  <div class="right-sec signup-form">
    <h2>Sign Up</h2>
    <form action="register" method="post">
      <div>
        <label for="fname">First Name</label>
        <input type="text" name="fname" id="fname">
      </div>
      <div>
        <label for="lname">Last Name</label>
        <input type="text" name="lname" id="lname">
      </div>
      <div>
        <label for="username">User Name</label>
        <input type="text" name="username" id="username">
      </div>
      <div>
        <label for="account_num">Account Number</label>
        <input type="number" name="account_num" id="account_num" min="0">
      </div>
      <div id="userinfodiv">
        <label for="userinfo">Select customer type:</label>
        <select name="userinfo" id="userinfo">
          <option value="0">Religious Place</option>
          <option value="1">Normal User</option>
          <option value="2">Business User</option>
        </select>
      </div>
      <div>
        <label for="email">Email</label>
        <input type="email" name="email" id="email">
      </div>
      <div class="last">
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
      </div>
      <input type="hidden" name="usertype" value="2">
      <span>
                    <a href="login.jsp">Log In</a>
                </span>
      <button type="submit">Sign Up</button>
    </form>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script type="text/javascript">
  var status = document.getElementById("status").value;
  if (status=="success") {
    Swal.fire("Congrats", "Account Created Successfully", "success");
  } else if(status != null && status == "failed") {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Something went wrong!'
    })
  } else {

  }
</script>
</body>
</html>
