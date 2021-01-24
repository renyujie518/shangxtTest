package PreparedStatement;

import entity.User;
import java.sql.*;
import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName LoginTest.java
 * @Description 模拟登陆
 * @createTime 2021年01月24日 18:46:00
 *
 *PreparedStatement
 * public interface PreparedStatement extends Statement {
 * 这里用更安全的方式（占位符）避免mysql注入的错误，同时速度更快
 */
public class LoginTest {
    /*
    模拟前台
     */
    public static void main(String[] args) {
        //输入用户密码
        Scanner input = new Scanner(System.in);
        System.out.println("请输入用户名");
        String userId = input.next();
        System.out.println("请输入密码");
        String password = input.next();
        //传入后台判断
        User user = login(userId, password);
        if (user == null){
            System.out.println("登录失败");
        }else {
            System.out.println("欢迎您"+user.getRealName());
        }


    }
    public static User login(String userID,String passWord){
        Connection coon = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user  = null;   //默认登录失败
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
            String username = "root";
            String password = "12345678";
            coon = DriverManager.getConnection(url, username, password);
            //创建命令发送器
           // String sql = "select * from t_user where userid = '"+userID+"' and password = '"+passWord+"';";
            String sql = "select * from t_user where userid = ? and password = ?";
            pstmt = coon.prepareStatement(sql);
            pstmt.setString(1,userID);
            pstmt.setString(2,passWord);
            rs = pstmt.executeQuery();
            //处理结果 由于查询语句中带了主键，所以成功的话只会返回一条数据，就不用while了
            if (rs.next()) {
                String realname = rs.getString("realname");
                double money = rs.getDouble("money");
                user = new User(userID, realname, passWord, money);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (rs!=null)  //防止空指针异常
                    rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (pstmt !=null)
                    pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (coon!=null)
                    coon.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }
}
