package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdate_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer,questionId;
        Question_Dao dao=new Question_Dao();
        Question question=null;
        int result=0;
        //调用请求对象读取请求参数
        title=request.getParameter("title");
        optionA=request.getParameter("optionA");
        optionB=request.getParameter("optionB");
        optionC=request.getParameter("optionC");
        optionD=request.getParameter("optionD");
        answer=request.getParameter("answer");
        questionId=request.getParameter("questionId");
        System.out.println("---------------------------");
        System.out.println(questionId);
        //调用Dao将数据推送到数据库，进行实时的更新
        question=new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
        result=dao.update(question,request);
        //调用info.jsp将操作结果写入到响应体中
        if (result==1){
            request.setAttribute("info","试题更新成功");
        }else {
            request.setAttribute("info","试题更新失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
