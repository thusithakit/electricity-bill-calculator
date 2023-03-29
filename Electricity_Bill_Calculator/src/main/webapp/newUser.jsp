<%--
  Created by IntelliJ IDEA.
  User: thusi
  Date: 3/8/2023
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
</head>
<body>
<h1>Add User</h1>
<form action="UserServlet" method="post">
    <p>
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="fname" required>
    </p>
    <p>
        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="lname" required>
    </p>
    <p>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </p>
    <p>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </p>
    <p>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </p>
    <p>
        <label for="usertype">User Type:</label>
        <select id="usertype" name="usertype">
            <option value=1>Admin</option>
            <option value=2>User</option>
        </select>
    </p>
    <p>
        <button type="submit">Add User</button>
    </p>
</form>
</body>
</html>
