package Entity;

import GongJu.UsersDao;

public class Add_Test {
    public static void main(String[] args) {
        String username,password,sex,email;
        Users users=new Users(null,"yanganjiao","123","女","yanganjiao123");
        UsersDao usersDao=new UsersDao();
        int i=usersDao.add(users);
        if (i==1){
            System.out.println("数据插入成功");
        }else {
            System.out.println("数据插入失败");
        }
    }
}
