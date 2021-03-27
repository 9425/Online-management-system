<%@ page import="java.util.List" %>
<%@ page import="Entity.Question" %>
<%@ page import="Entity.Numbers" %><%--
  Created by IntelliJ IDEA.
  User: yang hui
  Date: 2020/11/5
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>question</title>
</head>
<body>
<center>
    <%
        Numbers numbers=new Numbers();
        List<Question> questionList=(List)request.getAttribute("key");
    %>
    <table border="2" align="center">
        <tr align="center">
            <td>试题编号</td>
            <td>题目信息</td>
            <td>A选项</td>
            <td>B选项</td>
            <td>C选项</td>
            <td>D选项</td>
            <td>正确答案</td>
            <td colspan="2">操作</td>
        </tr>
        <%
            for (Question q:questionList){
        %>
        <%
            int i=numbers.increase(1);
            if(i%2==0){//这里的i虽然定义好了，但是他可能不代表每一行的数据
        %>
        <tr style="background-color:green">
        <%
	        }else{
        %>
            <tr style="background-color:yellow">
        <%
		}
		%>
        <tr>
        <td><%=q.getQuestionId()%></td>
        <td><%=q.getTitle()%></td>
        <td><%=q.getOptionA()%></td>
        <td><%=q.getOptionB()%></td>
        <td><%=q.getOptionC()%></td>
        <td><%=q.getOptionD()%></td>
        <td><%=q.getAnswer()%></td>
        <td><a href="/questionDelete?questionId=<%=q.getQuestionId()%>">删除试题</a></td>
            <td><a href="/questionFindById?questionId=<%=q.getQuestionId()%>">详细信息</a></td>
        </tr>
        <%
            }
        %>
    </table>
</center>
</body>
</html>
