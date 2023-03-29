<%@ page import="com.users.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: thusi
  Date: 3/15/2023
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    if (session.getAttribute("name")==null) {
        response.sendRedirect("login.jsp");
    }
%>
<% String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};%>
<% int date = new Date().getMonth();
    String monthName = month[date];
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> users = (List<User>) session.getAttribute("users"); %>
<html>
<head>
    <title>Electricity Bill Calculator | Bill Reader</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<nav>
    <div class="logo">
        <img src="./images/ceb-logo.png" alt="">
        <h1>Electricity Bill Calculator</h1>
    </div>
    <h2>Bill Reader Dashboard</h2>
    <div class="log">
        <p>Welcome, <%=session.getAttribute("name")%> !</p>
        <a href="logout">Logout</a>
    </div>
</nav>
<div class="container bill-reader">
    <form method="post" action="updateBill">
        <label for="customer">Please Select the account number for relevant customer:</label>
        <select id="customer" name="customer">
                <% for (User user : users) {%>
            <option value="<%= user.getAccountNumber()%>">
                <p><%= user.getAccountNumber()%></p>
            </option>
                <%}%>
        </select>
        <div>
            <label for="current_month">Enter the readings for the month of <%=monthName%>:</label>
            <input type="text" name="current_month" id="current_month" pattern="[0-9]+"
                   oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" >
        </div>
        <button type="submit">Update Reading</button>
    </form>
</div>

</body>
</html>
