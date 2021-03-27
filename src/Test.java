import java.sql.*;

public class Test {
    //import java.sql.*;
    /*
    第一步：注册驱动（告诉java程序，即将连接的是哪个品牌的数据库）
	第二步；获取连接（表示jvm的进程和数据库进程之间的通道打开了，进程之间的通信，使用之后要关闭通道）
    第三步：获取数据库操作对象（专门执行sql语句的对象）
	第四步：执行sql语句（执行dql dml...）
	第五步：处理查询结果（只有第四步执行的是select语句，才有第五步处理查询结果）
	第六步：释放资源（使用完资源之后，一定要关闭资源，java和数据库之间数据进程通道）
     */
        //给要关闭资源的接口做标记

        public static void main(String[] args) {
            //接下来我们注册驱动（告诉java程序我们要连接的数据库）
            Connection conn=null;
            Statement statement=null;//标记已经做好了
            try {
                Driver driver=new com.mysql.jdbc.Driver();
                DriverManager.registerDriver(driver);
                //通过以上两步骤，驱动已经注册好了，接下来进行连接
                //统一资源定位符
                String url="jdbc:mysql://localhost:3306/yajyh";
                //用户
                String user="root";
                //数据库密码
                String password="yajyh20";
                //与数据库进行连接
                //System.out.println("数据库连接对象");
                conn=DriverManager.getConnection(url,user,password);
                System.out.println("数据库连接对象"+conn);
                //以上数据库连接成功之后，需要使用一个类对其进行操作,也就是获取数据库操作对象
                statement=conn.createStatement();
                String sql="update dept set dname='设计部',loc='安顺' where deptno=20";
                int count=statement.executeUpdate(sql);
                System.out.println(count==1?"修改成功":"修改失败");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //将前面使用的资源进行关闭
                if (statement!=null){
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn!=null){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

}
