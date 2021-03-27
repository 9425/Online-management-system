package GongJu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Dept_DAO {
    JavaUtil javaUtil=new JavaUtil();
    Scanner sc=new Scanner(System.in);
    //增加数据库数据
    public void insert_into(){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="insert into dept(deptno,dname,loc) values(?,?,?)";
        PreparedStatement ps=javaUtil.getStatement(sql);
        System.out.println("*****请输入新建部门编号*****");
        int deptno=sc.nextInt();
        System.out.println("*****请输入新建部门名称*****");
        String dname=sc.next();
        System.out.println("*****请输入新建部门地址*****");
        String loc=sc.next();
        try {
            ps.setInt(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            int flag=ps.executeUpdate();
            if (flag==1){
                System.out.println("*****插入数据成功*****");
            }else {
                System.out.println("*****插入数据失败*****");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close();
        }
    }
    //删除数据库数据
    public void delete(){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="delete from dept where deptno=?";
        PreparedStatement ps=javaUtil.getStatement(sql);
        System.out.println("*****请输入需要删除的部门编号*****");
        int deptno=sc.nextInt();
        try {
            ps.setInt(1,deptno);
            int flag=ps.executeUpdate();
            if (flag==1){
                System.out.println("*****删除成功*****");
            }else{
                System.out.println("*****删除失败*****");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close();
        }
    }
    //更新数据库数据
    public void update(){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="update dept set dname=?,loc=? where deptno=?";
        PreparedStatement ps=javaUtil.getStatement(sql);
        System.out.println("*****请输入要修改的部门代码*****");
        int deptno=sc.nextInt();
        System.out.println("*****请输入要更改后的部门名称*****");
        String dname=sc.next();
        System.out.println("*****请输入更改后的部门地址*****");
        String loc=sc.next();
        try {
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setInt(3,deptno);
            int flag=ps.executeUpdate();
            if (flag==1){
                System.out.println("*****部门更新成功*****");
            }else {
                System.out.println("*****部门更新失败*****");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close();
        }
        //ps.setString();
    }
    //查询数据库数据
    public void select(){
        //JavaUtil javaUtil=new JavaUtil();
        javaUtil.getConnection();
        String sql="select * from dept";
        PreparedStatement ps=javaUtil.getStatement(sql);
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                int deptno=rs.getInt(1);
                String dname=rs.getString(2);
                String loc=rs.getString(3);
                System.out.println(deptno+" "+dname+" "+loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(rs);
        }
    }
}
