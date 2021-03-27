package Entity;

import GongJu.JavaUtil;
import GongJu.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAdd_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username,password,sex,email;
        int result;
        PrintWriter out;
        //调用请求对象得到用户的请求参数
        username=request.getParameter("username");
        password=request.getParameter("password");
        sex=request.getParameter("sex");
        email=request.getParameter("email");

        //调用userDao将用户信息填充到INSERT命令，并借助JDBC规范发送到数据库服务器
        UsersDao dao=new UsersDao();
        Date startDate=new Date();
        Users users=new Users(null,username,password,sex,email);
        result=dao.add(users,request);
        Date endDate=new Date();
        System.out.println("添加消耗时间为："+(endDate.getTime()-startDate.getTime()));

        //调用相应对象将处理结果以二进制形式写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        /*
        out.print(username);
        out.print("<br>");
        out.print(password);
        out.print("<br>");
        out.print(sex);
        out.print("<br>");
        out.print(email);
        out.print("<br>");
        out.print(users);
        out.print("<br>");
        out.print(result);
        //调用UserDao将用户信息填充到INSERT命令，并发送到mysql服务器
        /*
        Users users=new Users(null,username,password,sex,email);
        result=dao.add(users);

        //调用响应对象将处理结果以二进制写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        */
    }
}
