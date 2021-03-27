package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindById_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        Question_Dao dao=new Question_Dao();
        Question question;
        //调用请求对象读取请求头中的参数
        questionId=request.getParameter("questionId");
        //调用Dao将查询命令推送到数据库中查询详细信息
        question=dao.findById(questionId,request);
        //调用question_update.jsp将试题信息写入到响应体中
        request.setAttribute("key",question);
        request.getRequestDispatcher("/question_update.jsp").forward(request,response);
    }
}
