package Entity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Exam_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        List<Question> questionList=null;
        int score=0;
        //从当前用户私人储物柜的到系统提供的四道题目信息
        questionList=(List)session.getAttribute("key");
        //从请求包张读取四道题目的答案
        for (Question question:questionList){
            String answer=question.getAnswer();
            Integer questionId=question.getQuestionId();
            String userAnswer=request.getParameter("answer_"+questionId);
            if (userAnswer.equals(answer)){
                score+=25;
            }
        }
        //将分数写入到request中，作为共享数据
        request.setAttribute("info","本次考试成绩"+score);
        request.getRequestDispatcher("info.jsp").forward(request,response);
    }
}
