package Listener;

import GongJu.JavaUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.swing.text.html.HTMLDocument;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Servlet_Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public Servlet_Listener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      /*在前端与数据库进行交流的时候，为避免在连接通道中不断的产生交通运输
      工具，因此在启动Tomcat的时候，就预先提供一定数量的交通工具，使得不用
      重复的产生交通工具，节省时间

       */
        System.out.println("创建了全局作用域对象...");
        JavaUtil util=new JavaUtil();
        //创建一个map集合存储所产生的连接通道
        Map map=new HashMap();
        for (int i=0;i<20;i++){
            Connection con=util.getConnection();
            System.out.println("在启动服务器的时候，创建Conncetion："+con);
            map.put(con,true);//将创建的连接通道放置于map集合中，key的位置，对value值进行判断，
            //看是否可以使用此通道
        }
        //Connection连接通道创建完毕之后，为了使得Connection一直可以进行使用，将map集合放到全局作用域对象中
        //首先获取全局作用域对象
        ServletContext application=sce.getServletContext();
        application.setAttribute("key1",map);
    }

    //map的销毁：当服务器关闭的时候，Conncetion已经没有存在的必要了，因此需要进行关闭
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("全局作用域对象正在销毁...");
        //要将全局作用域对象进行销毁，首先需要从全局作用域对象中获得全局作用域对象中的map集合
        ServletContext application=sce.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        //将map集合放入到迭代器中进行迭代
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            Connection con=(Connection)it.next();
            if (con!=null){
                System.out.println(con);
                con=null;
                System.out.println("连接通道正在关闭...");
            }
        }
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
