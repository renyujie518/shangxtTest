package test;

import entity.Employee;
import mapper.Dongtaichaxun;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestDongtaichaxun.java
 * @Description TODO
 * @createTime 2021年02月05日 21:24:00
 */
public class TestDongtaichaxun {
    public static void main(String[] args) throws IOException {
        //解析mybatis.xml
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //获得session工厂
        SqlSessionFactory factor = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session对象
        SqlSession sqlSession = factor.openSession(true);//自动提交
        Dongtaichaxun mapper = sqlSession.getMapper(Dongtaichaxun.class);


        //if（where与此类似）
        List<Employee> list1 = mapper.selectIf(0, "");//执行1=1 查询所有
        System.out.println(list1);
        List<Employee> list2 = mapper.selectIf(7369, "");//只按照empno查询
        System.out.println(list2);
        List<Employee> list3 = mapper.selectIf(0, "scott");//只按照ename查询
        System.out.println(list3);
        List<Employee> list4 = mapper.selectIf(7369, "smith");//两个都输入都用
        System.out.println(list4);

        System.out.println("=====================");

        //when  类似if..else if...只要有一个条件满足，下面的都不执行了,什么都不满足执行otherwise
        List<Employee> list5 = mapper.selectWhen(0, "");//查询所有
        System.out.println(list5);
        List<Employee> list6 = mapper.selectWhen(7369, "");//只按照empno查询
        System.out.println(list6);
        List<Employee> list7 = mapper.selectWhen(0, "scott");//只按照ename查询
        System.out.println(list7);
        List<Employee> list8 = mapper.selectWhen(7939, "1111");//两个都输入都用
        System.out.println(list8);

        System.out.println("=====================");

        //set适合修改,自动增加set关键字同时去除最后一个逗号,ename不传就不改，job不传就不改
        Employee employee1 = new Employee();
        employee1.setEmpno(7939);
        employee1.setEname("ryj");//job不传就不改
        mapper.updateSet(employee1);
        Employee employee2 = new Employee();
        employee2.setEmpno(7939);
        employee2.setJob("lol");//ename不传就不改，
        mapper.updateSet(employee2);
        Employee employee3 = new Employee();
        employee3.setEmpno(7939);
        employee3.setEname("ryj1");
        employee3.setJob("lol1");//两个都传两个都改,但不能两个都不传
        mapper.updateSet(employee3);
        System.out.println("=====================");

        //foreach
        List<Integer> list = new ArrayList<>();
        list.add(7369);
        list.add(7499);
        list.add(7939);
        List<Employee> list9 = mapper.selectForeach(list);
        System.out.println(list9);
        System.out.println("=====================");

        //bind
        List<Employee> list10 = mapper.selectBind("A", "T");
        System.out.println(list10);
        List<Employee> list11 = mapper.selectBind("", "T");
        System.out.println(list11);
        List<Employee> list12 = mapper.selectBind("A", "");
        System.out.println(list12);
        List<Employee> list13 = mapper.selectBind("", "");
        System.out.println(list13==list1);
        System.out.println("=====================");



        sqlSession.close();
    }
}
