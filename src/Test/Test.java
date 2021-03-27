package Test;

import GongJu.Dept_DAO;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception{
        //创建工具对象
        //JavaUtil gongJu=new JavaUtil();
        //使用工具创建连接通道
        //gongJu.getConnection();
        /*
        String sql="insert into dept (deptno,dname,loc) values(?,?,?)";
        //后续的一系列操作都是通过在通道中跑动的对象进行实现，因此我们需要将通道对象进行获得
        PreparedStatement ps=gongJu.getStatement(sql);
        ps.setInt(1,100);
        ps.setString(2,"航空部");
        ps.setString(3,"云南");
        //此时所需要插入的对象已经准备就绪，只需要进行发送即可
        ps.executeUpdate();

        //如果还需要进行增删改查只需要重复上述步骤：即向将箱子(sql语句)放到通道中的运输对象上，然后往箱子中填充货物(补充?内容),最后进行发送即可
        String sql1="insert into dept(deptno,dname,loc) values(?,?,?)";
        PreparedStatement ps= gongJu.getStatement(sql1);
        ps.setInt(1,110);
        ps.setString(2,"机械部");
        ps.setString(3,"四川");
        ps.executeUpdate();
       */
        Dept_DAO dao=new Dept_DAO();
        //dao.insert_into();
        //dao.delete();
        //dao.update();
        //dao.select();
        //实现部门管理系统，部门管理系统能够对部门进行增删改查
        while (true){
            System.out.println("*****欢迎使用我公司部门管理系统*****");
            System.out.println("*****新增部门数据请输入1*****");
            System.out.println("*****删除部门数据请输入2*****");
            System.out.println("*****更改部门数据请输入3*****");
            System.out.println("*****查询部门信息请输入4*****");
            Scanner sc=new Scanner(System.in);
            int flag=sc.nextInt();
            if (flag==1){
                dao.insert_into();
            }else if (flag==2){
                dao.delete();
            }else if (flag==3){
                dao.update();
            }else if (flag==4){
                dao.select();
            }else {
                System.out.println("*****您输入的数据不合法，请重新输入");
            }
            System.out.println("*****退出系统请按1*****");
            System.out.println("*****返回上一步请按任意键*****");
            int flag2=sc.nextInt();
            if (flag2==1){
                System.out.println("*****您已退出系统，再见*****");
                break;
            }
        }
    }
}
