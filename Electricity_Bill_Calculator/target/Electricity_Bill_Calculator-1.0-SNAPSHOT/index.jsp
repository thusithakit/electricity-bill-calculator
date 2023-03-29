<%@ page import="com.users.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.users.User" %>
<%@ page import="java.util.Date" %>
<%
    if (session.getAttribute("name")==null) {
        response.sendRedirect("login.jsp");
    }
%>
<%
    Integer user_info = (Integer) session.getAttribute("user_info");
    Integer lastMonthReading = (Integer) session.getAttribute("last_month");
    Integer currentMonthReading = (Integer) session.getAttribute("current_month");

%>
<% String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};%>
<% int date = new Date().getMonth();
    String thisMonth = month[date];
    String lastMonth = month[date -1];
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% List<User> users = (List<User>) session.getAttribute("users"); %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electricity Bill Calculator | User</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<nav>
    <div class="logo">
        <img src="./images/ceb-logo.png" alt="">
        <h1>Electricity Bill Calculator</h1>
    </div>
    <h2>Customer Dashboard</h2>
    <div class="log">
        <p>Welcome, <%=session.getAttribute("name")%> !</p>
        <a href="logout">Logout</a>
    </div>
</nav>
<div class="container customer">
    <p>Reading for the month of <%=lastMonth%>: <%=lastMonthReading%></p>
    <p>Reading for the month of <%=thisMonth%>: <%=currentMonthReading%></p>
    <p>Your monthly Bill value is:
        <span>Rs.</span>
        <% if (user_info != null) {%>
        <span><%= com.units.UnitPriceDAO.calculateBillAmount(user_info, lastMonthReading, currentMonthReading) %></span>
        <% } %>
    </p>
</div>
</body>
</html>