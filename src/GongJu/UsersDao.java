package GongJu;

import Entity.Users;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private JavaUtil javaUtil=new JavaUtil();
    //用户注册
    //在JDBC规范中，Connection创建与销毁最为浪费时间(也就是连接通道的创建与销毁最为浪费时间)
    public int add(Users user){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="insert into users(username,password,sex,email) values(?,?,?,?)";
        PreparedStatement ps=javaUtil.getStatement(sql);
        int result=0;
        try {
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("数据插入成功");
            }else {
                System.out.println("数据插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close();
        }
        return result;
    }
    //程序的一般对扩展开放，对修改关闭
    //对于不合理的程序，修改方式，一般采用重载来进行
    public int add(Users user, HttpServletRequest request){
        //JavaUtil javaUtil=new JavaUtil();
        //javaUtil.getConnection();
        String sql="insert into users(username,password,sex,email) values(?,?,?,?)";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        int result=0;
        try {
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("数据插入成功");
            }else {
                System.out.println("数据插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return result;
    }

    //查询所有用户的信息
    public List findAll(){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="select * from users";
        PreparedStatement ps=javaUtil.getStatement(sql);
        ResultSet rs=null;
        List userList=new ArrayList();
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                int userId=rs.getInt("userId");
                String username=rs.getString("username");
                String password=rs.getString("password");
                String sex=rs.getString("sex");
                String email=rs.getString("email");
                Users users=new Users(userId,username,password,sex,email);
                userList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(rs);
        }
        return userList;
    }

    //根据用户编号删除用户信息
    public int delete(String userId){
        //JavaUtil javaUtil=new JavaUtil();
        //javaUtil.getConnection();
        String sql="delete from users where userId=?";
        PreparedStatement ps=javaUtil.getStatement(sql);
        int result=0;
        try {
            ps.setInt(1,Integer.valueOf(userId));
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close();
        }
        return result;
    }

    //登录验证
    public int login(String username,String password){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="select count(*) from users where username=? and password=?";
        PreparedStatement ps=javaUtil.getStatement(sql);
        ResultSet rs=null;
        int result=0;
        try {
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                result=rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(rs);
        }
        return result;
    }
}
