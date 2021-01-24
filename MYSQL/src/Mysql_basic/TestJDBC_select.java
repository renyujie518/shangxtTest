package Mysql_basic;

import entity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestJDBC_select.java
 * @Description TODO
 * @createTime 2021年01月24日 16:10:00
 */
public class TestJDBC_select {
    public static void main(String[] args) {
        /*
        模拟前台调用后台
        注意
           直接返回后台得到的结果集ResultSet不可取：
           1.报错，后台的finally会关闭结果集，前台无法使用
           2.不要试图将 关闭的操作放置前台，这种将JDBC的API直接传到前台会导致前后台耦合，换新技术的时候不好
           最好返回一种通用类型 ，比如List
         */
        List<Emp> emplist = finaAll();
        System.out.println("============这是前台显示结果,不管怎么变，前台都这么写+++++++++++++");
        System.out.println("编号\t姓名\t职位\t上级编号\t入职日期\t薪水\t奖金\t所属 部门编号");
        for (Emp emp : emplist) {
            System.out.println(emp.getEmpno()+"\t"+emp.getEname()+"\t"+emp.getJob()+"\t"
                +emp.getMgr()+"\t"+emp.getHiredate()+"\t"+emp.getSal()+"\t"+emp.getComm()+"\t"+emp.getDeptno());
        }

    }
    /*
    模拟后台

     */

    public static List<Emp> finaAll(){
        Connection coon = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = new ArrayList<>();
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
            //String sql ="delete from dept where  deptno = 50";
            String sql = "select * from emp";
            //发送
            //int n = stmt.executeUpdate(sql); //返回影响的行数,针对的是增删改
            rs = stmt.executeQuery(sql);
            //处理结果
            System.out.println("============这是后台显示结果+++++++++++++");
            System.out.println("编号\t姓名\t职位\t上级编号\t入职日期\t薪水\t奖金\t所属 部门编号");
            while (rs.next()){
                //获取当前行个列的数据
//                int empno = rs.getInt(1); //获取第一列
//                String ename = rs.getString(2);
//                String job = rs.getString(3);
//                int mgr = rs.getInt(4);
//                Date hiredate = rs.getDate(5);
//                double sal = rs.getDouble(6);
//                double comm = rs.getDouble(7);
//                int deptno = rs.getInt(8);

                int empno = rs.getInt("empno"); //不区分大小写
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                System.out.println(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno);

                //将对象封装为一个Emp对象
                Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
                //加到list中去
                list.add(emp);
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
                if (stmt !=null)
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
        return list;//数据已经添加到list中，再关闭结果集也没事
    }
}
