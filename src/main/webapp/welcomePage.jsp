<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="data.User" %>
<%
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Welcome page</h1>
        <p><strong>Hello <%= user.getName()%></strong></p>
        <p>Email: <%= user.getEmail()%></p>
    </body>
</html>
