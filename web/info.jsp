<%--
  Created by IntelliJ IDEA.
  User: yang hui
  Date: 2020/11/5
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>info</title>
</head>
<body>
<center>
    <%
        String result=(String) request.getAttribute("info");
    %>
    <font style="color: red;font-size: 45px">
        <%=result%>
    </font>
</center>
</body>
</html>
