<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <form action="/javaTask2/RegServletPattern" method="post">
            <label for="email">Email</label><br>
            <input type="email" id="email" name="email" required><br><br>

            <label for="password">Password</label><br>
            <input type="text" id="password" name="password" required><br><br>

            <button type="submit">Log in</button>

            <span>Or</span>
            <a href="signupPage.jsp">Sign up</a>
        </form>
        <%
            String alertMessage = (String) request.getAttribute("alertMessage");
            if (alertMessage != null) {
        %>
        <script>
            alert("<%= alertMessage%>");
        </script>
        <%
            }
        %>
    </body>
</html>
