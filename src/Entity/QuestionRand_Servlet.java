package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRand_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question_Dao dao=new Question_Dao();
        List questionList=null;
        HttpSession session=request.getSession(false);
        //调用Dao对象随机从question表拿四道题目
        questionList=dao.findRand(request);
        //将四道题目添加到request作为共享数据
        session.setAttribute("key",questionList);
        //请求转发，申请调用exam.jsp将题目传输给用户
        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
