<%@ page import="com.users.User" import="java.util.List" import="com.units.UnitPrice" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.units.UnitPriceDAO" %>
<% List<User> users = (List<User>) session.getAttribute("users"); %>
<%
    UnitPriceDAO unitPriceDAO = new UnitPriceDAO();
    List<UnitPrice> unitPrices = unitPriceDAO.getAllUnitPrices();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Electricity Bill Calculator | Super Admin</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body id="user-list">
<%--<h1 style="text-align: center;">Welcome to EBC, <%=session.getAttribute("name")--%>
<%--%> !</h1>--%>
<nav>
    <div class="logo">
        <img src="./images/ceb-logo.png" alt="">
        <h1>Electricity Bill Calculator</h1>
    </div>
    <h2>Admin Dashboard</h2>
    <div class="log">
        <p>Welcome, <%=session.getAttribute("name")%> !</p>
        <a href="logout">Logout</a>
    </div>
</nav>
<% if (session.getAttribute("name")==null) { %>
<% response.sendRedirect("login.jsp"); %>
<% } else { %>
<div class="container">
    <div class="menu">
        <h2>Users List</h2>
        <a href="./addUser.jsp">Add a New User</a>
    </div>
    <table cellpadding="0" cellspacing="0" border="0">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>User Type</th>
            <th></th>
        </tr>
        <% for (User user : users) { %>
        <% if (user.getUserType() != 0) { %>
        <tr>
            <td><%= user.getFname() %></td>
            <td><%= user.getLname() %></td>
            <td><%= user.getUsername() %></td>
            <td><% if (user.getUserType() == 1) { %>
                Bill Reader
                <% } else { %>
                Customer
                <% } %></td>
            <td>
                <form action="deleteUser" method="POST" class="del">
                    <input type="hidden" name="userId" value="<%= user.getId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% } %>
        <% } %>
    </table>
    <h2 class="bottom">Unit Prices for Relevant User Type</h2>
    <table>
        <tr>
            <th>User Info</th>
            <th>0-30</th>
            <th>31-60</th>
            <th>61-90</th>
            <th>91-120</th>
            <th>121-180</th>
            <th>180+</th>
        </tr>
        <% for (UnitPrice unitPrice : unitPrices) { %>
        <tr>
            <td><% if (unitPrice.getUser_info()==0){ %>
                <p>Religious Place</p>
                <%} else if (unitPrice.getUser_info()==1){%>
                <p>Normal User</p>
                <% } else {%>
                <p>Bussiness User</p>
                <% }%>
            </td>
            <td><%= unitPrice.getRange1() %> (per Unit)</td>
            <td><%= unitPrice.getRange2() %> (per Unit)</td>
            <td><%= unitPrice.getRange3() %> (per Unit)</td>
            <td><%= unitPrice.getRange4() %> (per Unit)</td>
            <td><%= unitPrice.getRange5() %> (per Unit)</td>
            <td><%= unitPrice.getRange6() %> (per Unit)</td>
        </tr>
        <% } %>
    </table>
</div>
<%--<h1>Users List</h1>--%>
<%--<table>--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>First Name</th>--%>
<%--        <th>Last Name</th>--%>
<%--        <th>Username</th>--%>
<%--        <th>User Type</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <% for (User user : users) { %>--%>
<%--    <% if (user.getUserType() != 0) { %>--%>
<%--    <tr>--%>
<%--        <td><%= user.getFname() %></td>--%>
<%--        <td><%= user.getLname() %></td>--%>
<%--        <td><%= user.getUsername() %></td>--%>
<%--        <td><% if (user.getUserType() == 1) { %>--%>
<%--            Admin--%>
<%--            <% } else { %>--%>
<%--            Customer--%>
<%--            <% } %></td>--%>
<%--        <td>--%>
<%--            <form action="deleteUser" method="POST">--%>
<%--                <input type="hidden" name="userId" value="<%= user.getId() %>">--%>
<%--                <input type="submit" value="Delete">--%>
<%--            </form>--%>
<%--        </td>--%>
<%--    </tr>--%>
<%--    <% } %>--%>
<%--    <% } %>--%>
<%--    </tbody>--%>
<%--</table>--%>

<%--<table>--%>
<%--    <tr>--%>
<%--        <th>User Info</th>--%>
<%--        <th>Range 1</th>--%>
<%--        <th>Range 2</th>--%>
<%--        <th>Range 3</th>--%>
<%--        <th>Range 4</th>--%>
<%--        <th>Range 5</th>--%>
<%--        <th>Range 6</th>--%>
<%--    </tr>--%>
<%--    <% for (UnitPrice unitPrice : unitPrices) { %>--%>
<%--    <tr>--%>
<%--        <td><% if (unitPrice.getUser_info()==0){ %>--%>
<%--            <p>Religious Place</p>--%>
<%--        <%} else if (unitPrice.getUser_info()==1){%>--%>
<%--              <p>Normal User</p>--%>
<%--            <% } else {%>--%>
<%--            <p>Bussiness User</p>--%>
<%--            <% }%>--%>
<%--        </td>--%>
<%--        <td><%= unitPrice.getRange1() %></td>--%>
<%--        <td><%= unitPrice.getRange2() %></td>--%>
<%--        <td><%= unitPrice.getRange3() %></td>--%>
<%--        <td><%= unitPrice.getRange4() %></td>--%>
<%--        <td><%= unitPrice.getRange5() %></td>--%>
<%--        <td><%= unitPrice.getRange6() %></td>--%>
<%--    </tr>--%>
<%--    <% } %>--%>
<%--</table>--%>


<% } %>
<%--<a--%>
<%--        class="nav-link py-3 px-0 px-lg-3 rounded" href="logout">Logout</a>--%>

<script>

</script>
</body>
</html>
