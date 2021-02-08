package test;

import entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestADU.java
 * @Description TODO
 * @createTime 2021年02月05日 11:36:00
 */
public class TestADU {
    public static void main(String[] args) throws IOException, ParseException {
        //解析mybatis.xml
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //获得session工厂
        SqlSessionFactory factor = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session对象
        SqlSession sqlSession = factor.openSession(true);//自动提交

        //添加
//        Scanner input = new Scanner(System.in);
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date hiredate = null;
//        hiredate = sdf.parse("1997-04-01");
//        Employee employeenew = new Employee("111","22",2222,hiredate,2.0,0,10);
//        //Employee employeenew = new Employee("111", "22", 2000, new SimpleDateFormat("yyyy-MM-dd").parse("1997-04-01".toString()), 2.0, 0, 10);
//        //Employee employeenew = new Employee(1000,"111", "22", 2000, new Date(), 2.0, 0, 10);
//        int insert = sqlSession.insert("mapper.EMPMapper.insert", employeenew);
//        if (insert>0){
//            System.out.println("添加成功");
//        }else{
//            System.out.println("添加失败");
//        }

        //修改
//        HashMap<Object, Object> map1 = new HashMap<>();
//        map1.put("ename","renyujie");//这个a要传入EMPMapper.xml占位符的key
//        map1.put("empno",7839);
//        int update = sqlSession.update("mapper.EMPMapper.update",map1);
//        if (update>0){
//            System.out.println("修改成功");
//        }else{
//            System.out.println("修改失败");
//        }

        //删除
//        int delete = sqlSession.delete("mapper.EMPMapper.update", 7934);
//        if (delete>0){
//            System.out.println("修改成功");
//        }else{
//            System.out.println("修改失败");
//        }

        //关闭sqlSession
        sqlSession.close();
    }
}
