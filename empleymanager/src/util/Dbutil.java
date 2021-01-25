package util;

import entity.Employee;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Dbutil.java
 * @Description 复用代码
 * @createTime 2021年01月25日 13:35:00
 */
public  abstract class Dbutil {
    //定义一个日志记录器
    private static Logger logger = Logger.getLogger(Dbutil.class.getName());
    private Dbutil(){
        //私有的构造方法，连子类都不能有，因为如果有子类，父类没有无参 构造方法（public的）
    }

    static String dirver = "";
    static String user = "";
    static String url = "";
    static String password = "";
    static {
        //每次连接数据库要访问properti文件，效率不高，放置到静态代码块中加载一次即可
        Properties properties = new Properties();
        InputStream is = Dbutil.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(is);
            logger.info("正确读取jdbc.propertities"+properties);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取jdbc.properties出错"+e.toString());
        }
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        dirver = properties.getProperty("dirver");

    }

    public  static Connection getConnection(){
        Connection coon = null;
        try {
            Class.forName(dirver);
            coon = DriverManager.getConnection(url, user, password);
            logger.info("获取数据库连接成功"+coon);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("数据库连接失败"+throwables.toString());
        }
        return coon;
    }

    public  static void  closeAll(ResultSet rs, Statement stmt,Connection coon){
        try {
            if (rs != null){
                rs.close();
                logger.debug("关闭结果集成功");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (stmt != null){
                stmt.close();
                logger.debug("关闭Statement成功");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (coon!=null){
                coon.close();
                logger.debug("关闭数据库成功");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //inser delete update
    public static  int Execute(String sql,Object[] params) {
        Connection coon = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int n =0;//返回值，默认添加失败

        try {
            coon = Dbutil.getConnection();
            pstmt = coon.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1,params[i]);
            }
            n = pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            logger.error("数据库操作失败"+e.toString());
        }finally {
            Dbutil.closeAll(rs,pstmt,coon);  //注意,这里可以直接填pmt,因为在closeAll里的传参是父类Statement
        }
        return n;
    }
}
