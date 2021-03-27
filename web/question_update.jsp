<%@ page import="Entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: yang hui
  Date: 2020/11/5
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>question_update</title>
</head>
<body>
<center>
    <%
        Question q=(Question) request.getAttribute("key");
    %>
    <form action="/questionUpdate" method="get">
        <table border="2">
            <tr>
                <td>试题编号</td>
                <td><input type="text" name="questionId" value="<%=q.getQuestionId()%>" readonly/></td>
            </tr>
            <tr>
                <td>题目:</td>
                <td><input type="text" name="title" value="<%=q.getTitle()%>"/></td>
            </tr>
            <tr>
                <td>A:</td>
                <td><input type="text" name="optionA" value="<%=q.getOptionA()%>"/></td>
            </tr>
            <tr>
                <td>B:</td>
                <td><input type="text" name="optionB" value="<%=q.getOptionB()%>"/></td>
            </tr>
            <tr>
                <td>C:</td>
                <td><input type="text" name="optionC" value="<%=q.getOptionC()%>"/></td>
            </tr>
            <tr>
                <td>D:</td>
                <td><input type="text" name="optionD" value="<%=q.getOptionD()%>"/></td>
            </tr>
            <tr>
                <td>正确答案:</td>
                <td>
                    <input type="radio" name="answer" value="A" <%="A".equals(q.getAnswer())?"checked":""%>/>A
                    <input type="radio" name="answer" value="B" <%="B".equals(q.getAnswer())?"checked":""%>/>B
                    <input type="radio" name="answer" value="C" <%="C".equals(q.getAnswer())?"checked":""%>/>C
                    <input type="radio" name="answer" value="D" <%="D".equals(q.getAnswer())?"checked":""%>/>D
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="更新试题..."></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
