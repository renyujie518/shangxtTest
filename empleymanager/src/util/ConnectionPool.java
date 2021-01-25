package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ConnectionPool.java
 * @Description 数据库连接池(10个)
 * @createTime 2021年01月25日 21:28:00
 *
 * 获取连接  线程池中的元素出队 删除第一个
 * 关闭连接  线程池中的元素入队 添加到末尾
 */
public class ConnectionPool {
    private static LinkedList<Connection> list = new LinkedList<Connection>();

    static {
        //调用线程池这个类，就必须先执行此静态代码块，建立多个数据库连接放入list
        String driver ="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "12345678";

        try {
            Class.forName(driver);  //加载驱动
            for (int i = 0; i < 10; i++) {
                Connection coon = newConnection();
                list.add(coon);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Connection coon = getConnection();//1
        System.out.println(coon);
        System.out.println(getConnection());//2
        System.out.println(getConnection());//3
        returnConnection(coon);//还一个
        System.out.println(getConnection());//4
        System.out.println(getConnection());//5
        System.out.println(getConnection());//6
        System.out.println(getConnection());//7
        System.out.println(getConnection());//8
        System.out.println(getConnection());//9
        System.out.println(getConnection());//10
        System.out.println(getConnection());//11 超过十个了，但之前还过一个，所以地址值必然与上述还的那个1相同
        System.out.println(getConnection());//12 归还的也没有了，建立新的物理连接

    }

    //连接池中没有连接的时候新建连接,static中建立新连接
    private static Connection newConnection(){
        String url="jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String user = "root";
        String password = "12345678";
        Connection coon = null;
        try {
            coon = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coon;
    }

    //获取连接
    public static Connection getConnection(){
        if (list.size()>0){
            return list.removeFirst(); //有空闲连接直接返回list的
        }else {
            return newConnection(); //没有空闲连接，不等待了，直接获取物理连接
        }

    }

    //数据路连接使用完毕，放回连接池
    public static void  returnConnection(Connection coon){//静态方法，通过类名可以直接调用
        if (list.size()<10){
            list.addLast(coon);//小于10个，入队，放置末尾
        }else {
            try {
                coon.close();  //如果连接池满了直接物理关闭
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
