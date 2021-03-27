package Fileter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Servlet_Filter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        //拦截后，通过请求对象向Tomcat索要当前用户的HttpSession
        //通过用户是否拥有session进行用户身份的判断是否合法

        //在前端请求包中的请求行里面，有一个url属性，通过对url进行判断，是否包含login字段才决定是否进行拦截
        /*
        if (session==null){
            request.getRequestDispatcher("/login_error.html").forward(req,resp);
            return;
        }
        //如果拥有session则进行放行
        chain.doFilter(req, resp);
        */
        /*
        以上的代码有极大的问题，也就是在进行登录页面操作的时候，登录页面的展示也会
        被阻拦，造成无法进行登录，因为登录的相关文件均与login关键字进行相关联，所以通过对
        url中是否由login进行判断是否进行阻拦
         */
        //如果用户访问的是登录相关文件，则进行以下操作
        HttpSession session;
        String uri=request.getRequestURI();//uri包含url
        if (uri.indexOf("login")!=-1){
            chain.doFilter(req,resp);
            return;
        }

        //如果用户访问的是与登录不相关的文件，则进行以下操作
        session=request.getSession(false);
        if (session!=null){
            chain.doFilter(req,resp);
            return;
        }
        request.getRequestDispatcher("/login_error.html").forward(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
