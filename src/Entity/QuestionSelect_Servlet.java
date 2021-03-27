package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionSelect_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question_Dao dao=new Question_Dao();
        //调用dao对象将查询命令推送到数据库得到试题信息
        List questionList=dao.findAll(request);
        //通过请求转发，向tomcat所要question.jsp,将试题信息写入到JSP中
        request.setAttribute("key",questionList);
        request.getRequestDispatcher("/question.jsp").forward(request,response);
    }
}
