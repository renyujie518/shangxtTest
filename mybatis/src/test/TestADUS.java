package test;

import entity.Employee;
import mapper.EmpADUS;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestADUS.java
 * @Description 利用接口调用xml中的语句  mapper代理  xml类似接口的实现类
 * @createTime 2021年02月05日 17:04:00
 */
public class TestADUS {
    public static void main(String[] args) throws IOException, ParseException {
        //解析mybatis.xml
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //获得session工厂
        SqlSessionFactory factor = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session对象
        SqlSession sqlSession = factor.openSession(true);//自动提交

        //调接口定义的方法
        EmpADUS mapper = sqlSession.getMapper(EmpADUS.class);//代理  EmpADUS是一个接口


        List<Employee> list = mapper.selectAll();
        System.out.println(list);

//        Employee employee = new Employee();
//        employee.setEname("1111");
//        employee.setJob("sdasd");
//        employee.setMgr(2222);
//
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date hiredate = sdf.parse("1997-04-01");
//        employee.setHiredate(hiredate);
//
//        //employee.setHiredate(null);
//        employee.setSal(111.0);
//        employee.setComm(null);
//        employee.setDeptno(10);
//
//        mapper.insert(employee);
//        List<Employee> list2 = mapper.selectAll();
//        System.out.println(list2);

        //多参数传递
        //List<Employee> list3 = mapper.selectBy2p("manager", 7839);
        Employee clerk = mapper.selectBy2p("clerk", 7902);
        System.out.println(clerk);

        //传参是个对象，则内部会调用get方法，所以在xml可以直接取表名（实体类的 privite 变量）
        List<Employee> list3 = mapper.selectByObject(clerk);
        System.out.println(list3);


        //关闭sqlSession
        sqlSession.close();
    }
}
