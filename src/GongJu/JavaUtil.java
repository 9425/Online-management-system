package GongJu;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class JavaUtil {
    private Connection con=null;
    private PreparedStatement ps=null;

    //同样的道理，使用重载来进行代码的修改
    //通过全局作用域对象得到Connection
    public  Connection getConnection(HttpServletRequest request){
        //通过请求对象，得到全局作用域对象
        ServletContext application=request.getServletContext();
        //从全局作用域对象得到提前存储在全局作用域对象中的map集合
        Map map=(Map)application.getAttribute("key1");
        //从map处获得一个空闲状态的Connection
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            con=(Connection) it.next();//con是在此处进行的赋值，通过此处的赋值，可以将一个连接通道传递给con
            boolean flag=(boolean)map.get(con);
            if (flag==true){
                map.put(con,false);//对取出来的con再次进行重新的初始化，使得其状态显示为正在使用中
                break;
            }
        }
        return con;
    }
    public PreparedStatement getStatement(String sql,HttpServletRequest request){
            try {
                ps=getConnection(request).prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
    }

    public void close(HttpServletRequest request){
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application=request.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        //此处并没由将con通道进行销毁，而是放入到map集合中进行初始化，以待下一次备用
        map.put(con,true);//对此正在进行操作的通道进行初始化
    }

    //使用静态代码块进行注册
    static {
        ResourceBundle rs=ResourceBundle.getBundle("JDBC");
        String driver=rs.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("*****接口类已被注册*****");
    }
    //创建通道，以及指定通道管理者
    public  Connection getConnection(){
        ResourceBundle rs=ResourceBundle.getBundle("JDBC");
        String url=rs.getString("url");
        String user=rs.getString("user");
        String password=rs.getString("password");
       //Connection con= null;
        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println("*****连接通道创建成功*****");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("*****连接通道创建失败*****");
        }
        return con;
    }
    //创建通道中的数据传输对象
    public  PreparedStatement getStatement(String sql){
        con=getConnection();
        //PreparedStatement ps= null;
        try {
            ps = con.prepareStatement(sql);
            System.out.println("*****数据传输对象准备就绪*****");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("*****数据传输对象准备失败*****");
        }
        return ps;
    }
    //将所创建通道以及通道中所用工具关闭
    public void close(){
        if (ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //将所使用的数据展示对象进行关闭
    public void close(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
