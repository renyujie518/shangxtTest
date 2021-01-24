package Mysql_basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestJDBC.java
 * @Description TODO
 * @createTime 2021年01月24日 15:14:00
 */
public class TestJDBC_idu {
    public static void main(String[] args){
        Connection coon = null;
        Statement stmt = null;
        try{
            //添加驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立连接
            String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "12345678";
            coon = DriverManager.getConnection(url, user, password);
            System.out.println(coon);
            //创建命令发送器
            stmt = coon.createStatement();
            //测试sql语句
            //String sql = "insert into dept values(50,'教学部','北京')";
            //String sql = "update dept set  dname = '咨询部' where deptno = 50";
            String sql ="delete from dept where  deptno = 50";
            //发送
            int n = stmt.executeUpdate(sql); //返回影响的行数
            if (n>0){
                System.out.println("添加成功"+n);
            }else {
                System.out.println("添加失败"+n);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (stmt !=null)  //防止空指针异常
                    stmt.close();
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


    }
}
