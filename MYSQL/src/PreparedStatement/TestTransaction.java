package PreparedStatement;

import java.sql.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestTransaction.java
 * @Description 用事务保证转账安全
 * @createTime 2021年01月24日 19:53:00
 *
 *
 * 在JDBC中，事务操作缺省是自动提交。
 * 一条对数据库的DML(insert、update、delete)代表一项事务操作
 * 操作成功后，系统将自动调用commit()提交，否则自动调用rollback()回滚
 */
public class TestTransaction {
    public static void main(String[] args){
        Connection coon = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
            String username = "root";
            String password = "12345678";
            coon = DriverManager.getConnection(url, username, password);

            //设置事务不再自动提交
            coon.setAutoCommit(false);

            String sql1 = "update t_user set  money= money+? where userid = ?";
            String sql2 = "update t_user set  money= money-? where userid = ?";
            pstmt1 = coon.prepareStatement(sql1);
            pstmt2 = coon.prepareStatement(sql2);

            pstmt1.setInt(1,2000);
            pstmt1.setString(2,"lisi");
            //pstmt1.setString(2,"lisi22");
            pstmt2.setInt(1,2000);
            //pstmt2.setString(2,"zhangsan");
            pstmt2.setString(2,"zh222333");

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            //事务成功结束
            coon.commit();


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
            //事务失败结束，回滚
            System.out.println("发生回滚");
            try {
                coon.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }finally {
            try {
                pstmt1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                pstmt2.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                coon.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


//        Connection conn = null;
//        Statement stmt = null;
//        int n=0;
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
//            String username = "root";
//            String password = "12345678";
//            Connection coon = DriverManager.getConnection(url, username, password);
//            stmt = conn.createStatement();
//            stmt.executeUpdate("update t_user set money = money -2000 where userid = 'zhangsan'");
//            stmt.executeUpdate("update t_user set money = money1 +2000 where userid = 'lisi'");
//            conn.commit();
//        }catch (SQLException e){
//            try {
//                conn.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }finally {
//            //省略
//        }
    }
}
