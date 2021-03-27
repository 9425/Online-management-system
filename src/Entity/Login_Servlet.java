package Entity;

import GongJu.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username,password;
        UsersDao dao=new UsersDao();
        int result=0;
        //调用请求对象对请求体使用utf-8字符集进行重新编辑
        request.setCharacterEncoding("utf-8");
        //调用请求对象读取请求体参数
        username=request.getParameter("username");
        password=request.getParameter("password");
        //调用Dao将查询验证信息推送到数据库服务器上
        result=dao.login(username,password);
        //调用响应对象，根据验证结果将不同的资源文件地质写入到响应头，交给浏览器
        if (result==1){
            //在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户创建一个session
            HttpSession session=request.getSession();
            response.sendRedirect("/index.html");
        }else {
            response.sendRedirect("/login_error.html");
        }
    }

}
