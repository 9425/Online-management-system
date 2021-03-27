package GongJu;

import Entity.Question;
import org.omg.CORBA.Request;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Question_Dao{
    private JavaUtil javaUtil=new JavaUtil();
    public int add(Question question, HttpServletRequest request){
        String sql="insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        int result=0;
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("数据插入成功...");
            }else {
                System.out.println("数据插入失败...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return result;
    }

    public List findAll(HttpServletRequest request){
        String sql="select * from question";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        ResultSet rs;
        List list=new ArrayList();
        try {
            rs= ps.executeQuery();
            while (rs.next()){
                Integer questionId=rs.getInt("questionId");
                String title=rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer=rs.getString("answer");
                Question question=new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return list;
    }

    public int delete(String questionId,HttpServletRequest request){
        String sql="delete from question where questionId=?";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        int result=0;
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("删除成功...");
            }else {
                System.out.println("删除失败...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return result;
    }

    public Question findById(String questionId,HttpServletRequest request){
        String sql="select * from question where questionId=?";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        ResultSet rs;
        Question question=null;
        try {
            System.out.println(questionId);
            //int realId = Integer.parseInt(id.replaceAll(" ", ""));
            ps.setInt(1,Integer.valueOf(questionId));
            rs=ps.executeQuery();
            while (rs.next()){
                Integer qusetionId=rs.getInt("questionId");
                String title=rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer=rs.getString("answer");
                question=new Question(qusetionId,title,optionA,optionB,optionC,optionD,answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return question;
    }

    public int update (Question question,HttpServletRequest request){
        String sql="update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where questionId=?";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        int result=0;
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());
            result=ps.executeUpdate();
            if (result==1){
                System.out.println("试题更新成功...");
            }else {
                System.out.println("试题更新失败...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return result;
    }
    public List findRand(HttpServletRequest request){
        String sql="select * from question order by rand() limit 0,4";
        PreparedStatement ps=javaUtil.getStatement(sql,request);
        ResultSet rs=null;
        List list=new ArrayList();
        try {
            rs=ps.executeQuery();
            while (rs.next()){
                Integer questionId=rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA=rs.getString("optionA");
                String optionB=rs.getString("optionB");
                String optionC=rs.getString("optionC");
                String optionD=rs.getString("optionD");
                String answer = rs.getString("answer");
                Question question=new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            javaUtil.close(request);
        }
        return list;
    }
}
