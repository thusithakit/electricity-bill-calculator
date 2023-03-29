<%--
  Created by IntelliJ IDEA.
  User: thusi
  Date: 3/13/2023
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Electricity Bill Calculator | Super Admin</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<nav>
    <div class="logo">
        <img src="./images/ceb-logo.png" alt="">
        <h1>Electricity Bill Calculator</h1>
    </div>
    <h2>Admin Dashboard</h2>
    <div class="log">
        <p>Welcome, Thusithakit !</p>
        <a href="#">Logout</a>
    </div>
</nav>
<div class="container">
        <span class="tip">
            Tip : From here you can add new customers or bill readers for the system
        </span>
    <form action="addUser" method="POST" name="addUser">
        <div class="grid">
            <div>
                <label for="fname">First Name:</label>
                <input type="text" id="fname" name="fname">
            </div>

            <div>
                <label for="lname">Last Name:</label>
                <input type="text" id="lname" name="lname">
            </div>

            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" name="username"></div>

            <div>
                <label for="email">email:</label>
                <input type="text" id="email" name="email">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password">
            </div>

            <div>
                <label for="usertype">User Type:</label>
                <select id="usertype" name="usertype" onchange="showUserInfo()">
                    <option value="1">Bill Reader</option>
                    <option value="2">Customer</option>
                </select>
            </div>

            <div id="customer">
                <label for="account_number">account number:</label>
                <input type="text" id="account_number" name="account_number">
            </div>
            <div id="userinfodiv">
                <label for="userinfo">Select customer type:</label>
                <select name="userinfo" id="userinfo">
                    <option value="0">Religious Place</option>
                    <option value="1">Normal User</option>
                    <option value="2">Business User</option>
                </select>
            </div>
        </div>
        <!-- <input type="submit" value="Add User"> -->
        <div class="btns">
            <a href="./superAdmin.jsp">Go Back</a>
            <button type="submit">Add User</button>
        </div>
    </form>
</div>
<script>
    function showUserInfo() {
        let user = usertype[usertype.selectedIndex].value;
        let info = document.getElementById("userinfodiv");
        let customer = document.getElementById("customer");
        console.log(user);
        if (user == 2) {
            info.classList.add("show");
            customer.classList.add("show");
        } else {
            info.classList.remove("show");
            customer.classList.add("show");
        }
    }
</script>
</body>
</html>
