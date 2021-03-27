package Entity;

import GongJu.Question_Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDelete_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId;
        Question_Dao dao=new Question_Dao();
        int result=0;
        //调用请求对象读取请求头参数，得到试题编号
        questionId=request.getParameter("questionId");
        //调用Dao对象将删除命令推送到数据库服务器
        result=dao.delete(questionId,request);
        //调用info.jsp将处理结果写入到响应体
        if (result==1){
            request.setAttribute("info","试题删除成功...");
        }else {
            request.setAttribute("info","试题删除失败...");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
