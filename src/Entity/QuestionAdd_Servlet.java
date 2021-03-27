package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAdd_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        Question_Dao dao=new Question_Dao();
        Question question=null;
        int result=0;
        //调用请求对象读取请求信息，得到新增信息
        title=request.getParameter("title");
        optionA=request.getParameter("optionA");
        optionB=request.getParameter("optionB");
        optionC=request.getParameter("optionC");
        optionD=request.getParameter("optionD");
        answer=request.getParameter("answer");

        //调用Dao工具类中的插入函数，将Insert推送到数据库，并得到处理结果
        question=new Question(null,title,optionA,optionB,optionC,optionD,answer);
        result=dao.add(question,request);
        //将处理结果写入到请求作用域对象最为共享数据给JSP
        //通过请求转发，向Tomcat所要info.jsp将处理结果写入到响应体中
        if (result==1){
            request.setAttribute("info","试题添加成功");
        }else {
            request.setAttribute("info","试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
