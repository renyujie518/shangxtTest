package util;

import entity.Employee;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Dbutil.java
 * @Description 复用代码
 * @createTime 2021年01月25日 13:35:00
 */
public  abstract class Dbutil{
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

    public  static<T> List<T> executeQuery(String sql,Object[] params,String classname){
        Connection coon = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<T> list = new ArrayList<T>();
        try {
            coon = Dbutil.getConnection();
            pstmt = coon.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1,params[i]);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while (rs.next()){
                //Employee emp = new Employee();
                Class<?> clazz = Class.forName(classname);
                T entity = (T) clazz.newInstance();
                for (int i = 0; i < count; i++) {
                    //获取当前列的名称
                    String columnName = rsmd.getColumnName(i + 1);

                    //获取当前列的值  int empno = rs.getInt("empno"); //不区分大小写
                    Object value = rs.getObject(columnName);

                    //当前列的值赋值给entity 例如： emp.setEmpno(empno);
                    //这里观察发现entity包下的对象的赋值方法都是get+首字母大写+其余小写，所以这里要用到字符串拼接
                    //调用set方法时有时候是setint,有时候是setstring,setdate,所以要用metedate获取表结构的方式
                    //调用set方法的输入参数均只有一个，即根据列名找到的具体值value
                    String methodName = "set"+columnName.substring(0,1).toUpperCase()+
                            columnName.substring(1).toLowerCase();
                    Class<?> parameterType = Class.forName(rsmd.getColumnClassName(i + 1));
                    //参照之前练习的反射调用方法 Method m2 = clazz.getMethod（"add",int.class,String.class）
                    Method method = clazz.getMethod(methodName, parameterType);
                    method.invoke(entity, value);// = emp.setEmpno(empno);
                }

                list.add(entity);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            Dbutil.closeAll(rs,pstmt,coon);
        }
        return list;
    }
}
