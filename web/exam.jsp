<%@ page import="java.util.List" %>
<%@ page import="Entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: yang hui
  Date: 2020/11/9
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exam</title>
</head>
<body>
<center>
    <form action="/exam">
        <table align="center" border="2">
            <tr>
                <td>试题编号</td>
                <td>试题信息</td>
                <td>A</td>
                <td>B</td>
                <td>C</td>
                <td>D</td>
                <td colspan="2" align="center">答案</td>
            </tr>
            <%
                List<Question> questionList=(List)session.getAttribute("key");
                //EI是无法遍历集合的，因此在使用EI遍历集合时，必须使用传统的java语句
                for (Question question:questionList){
            %>
            <tr>
                <td><%=question.getQuestionId()%></td>
                <td><%=question.getTitle()%></td>
                <td><%=question.getOptionA()%></td>
                <td><%=question.getOptionB()%></td>
                <td><%=question.getOptionC()%></td>
                <td><%=question.getOptionD()%></td>
                <td>
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="A"/>A
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="B"/>B
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="C"/>C
                    <input type="radio" name="answer_<%=question.getQuestionId()%>" value="D"/>D
                </td>
            </tr>
            <%
                }
            %>
            <TR ALIGN="CENTER">
                <td colspan="3"><input type="submit" value="交卷"/></td>
                <td colspan="4"><input type="reset" value="重做"/></td>
            </TR>
        </table>
    </form>
</center>
</body>
</html>
